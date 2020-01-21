package com.bank.demo.service;

import com.bank.demo.dao.AccountRepository;
import com.bank.demo.dao.TransactionRepository;
import com.bank.demo.domain.Operation;
import com.bank.demo.domain.entity.Account;
import com.bank.demo.domain.entity.Transaction;
import com.bank.demo.sync.AccountSynchronizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentTransactionService {

    private static final Logger log = LoggerFactory.getLogger(PaymentTransactionService.class);

    @Autowired
    private AccountSynchronizer accountSynchronizer;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionValidator transactionValidator;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void transfer(String fromIban, String toIban, BigDecimal amount) {
        accountSynchronizer.execute(fromIban, toIban, () -> {
            transactionValidator.validateCreditorAndDebtorIbansDiffer(fromIban, toIban);
            transactionValidator.validateAmountPositive(amount);
            String uid = UUID.randomUUID().toString();
            credit(accountRepository.findAccountByIban(fromIban), amount, uid);
            debit(accountRepository.findAccountByIban(toIban), amount, uid);
        });
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void cancelTransfer(String transactionUid) {

        String creditorIban = accountRepository.findCreditorAccountUidByTransaction(transactionUid);
        String debtorIban = accountRepository.findDebtorAccountUidByTransaction(transactionUid);

        accountSynchronizer.execute(creditorIban, debtorIban, () -> {
            if (transactionRepository.findCancelled(transactionUid).iterator().hasNext()) {
                throw new RuntimeException("Transaction already cancelled");
            }
            Account accCreditor = accountRepository.findCreditorAccountByTransaction(transactionUid);
            Account accDebtor = accountRepository.findDebtorAccountByTransaction(transactionUid);
            BigDecimal amount = accCreditor.getTransactions().iterator().next().getAmount();
            cancelCreditor(accCreditor, amount, transactionUid);
            cancelDebtor(accDebtor, amount, transactionUid);
        });
    }

    private void debit(Account toAccount, BigDecimal amount, String uid) {
        toAccount.addTransaction(createCreditTransaction(amount, uid));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(toAccount);
    }

    private Transaction createCreditTransaction(BigDecimal amount, String uid) {
        Transaction tr = new Transaction();
        tr.setAmount(amount);
        tr.setUid(uid);
        tr.setOperation(Operation.CREDIT);
        return tr;
    }

    private void credit(Account fromAccount, BigDecimal amount, String uid) {
        transactionValidator.validateCreditAccountHasSufficientFunds(fromAccount.getBalance(), amount);
        fromAccount.addTransaction(createDebitTransaction(amount, uid));
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        accountRepository.save(fromAccount);
    }

    private Transaction createDebitTransaction(BigDecimal amount, String uid) {
        Transaction tr = new Transaction();
        tr.setAmount(amount);
        tr.setUid(uid);
        tr.setOperation(Operation.DEBIT);
        return tr;
    }

    private void cancelCreditor(Account account, BigDecimal amount, String uid) {
        transactionValidator.validateCreditAccountHasSufficientFunds(account.getBalance(), amount);
        account.addTransaction(createCancelTransaction(amount, uid));
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }

    private void cancelDebtor(Account account, BigDecimal amount, String uid) {
        account.addTransaction(createCancelTransaction(amount, uid));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    private Transaction createCancelTransaction(BigDecimal amount, String uid) {
        Transaction tr = new Transaction();
        tr.setAmount(amount);
        tr.setUid(uid);
        tr.setOperation(Operation.CANCEL);
        return tr;
    }

}