package com.bank.demo.service;

import com.bank.demo.dao.AccountRepository;
import com.bank.demo.domain.entity.Account;
import com.bank.demo.domain.model.NewAccountRequest;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void createAccount(NewAccountRequest acc) {
        Account account = new Account(acc.getIban(), acc.getCurrency(), acc.getBalance());
        accountRepository.save(account);
    }

    public Account findAccount(String iban) {
        return accountRepository.findAccountByIban(iban);
    }

    /*public void withdraw(Account account, BigDecimal amount) {
        account.account.getBalance().subtract(amount)
        balance = balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }*/

    public List<Account> findAccounts() {
        return IterableUtils.toList(accountRepository.findAll());
    }

}
