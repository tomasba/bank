package com.bank.demo.service;

import com.bank.demo.domain.entity.Account;
import com.bank.demo.domain.entity.TransactionDeposit;
import com.bank.demo.domain.entity.TransactionWithdraw;
import com.bank.demo.sync.AccountSynchronizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentTransactionService {

    @Autowired
    private AccountSynchronizer accountSynchronizer;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void transfer(String fromIban, String toIban, BigDecimal amount) {
        accountSynchronizer.execute(fromIban, toIban, () -> {
            String uid = UUID.randomUUID().toString();

            Account fromAccount = accountService.findAccount(fromIban);
            TransactionWithdraw transactionWithdraw = new TransactionWithdraw();
            transactionWithdraw.setAmount(amount);
            transactionWithdraw.setUid(uid);
            transactionWithdraw.setAccount(fromAccount);
            fromAccount.addWithdrawTransactions(transactionService.saveTransaction(transactionWithdraw));
            accountService.saveUpdate(fromAccount);

            Account toAccount = accountService.findAccount(toIban);
            TransactionDeposit transactionDeposit = new TransactionDeposit();
            transactionDeposit.setAmount(amount);
            transactionDeposit.setUid(uid);
            transactionDeposit.setAccount(toAccount);
            toAccount.addDepositTransactions(transactionService.saveTransaction(transactionDeposit));
            accountService.saveUpdate(toAccount);
        });
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void cancelTransfer(String iban, String transactionId) {
        accountSynchronizer.execute(iban, () -> {
            Account account = accountService.findAccount(iban);
        });
    }

}
