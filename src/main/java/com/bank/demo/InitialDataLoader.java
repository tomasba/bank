package com.bank.demo;

import com.bank.demo.dao.AccountRepository;
import com.bank.demo.dao.PaymentRepository;
import com.bank.demo.domain.Currency;
import com.bank.demo.domain.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitialDataLoader implements ApplicationRunner {

    private AccountRepository accountRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public InitialDataLoader(AccountRepository accountRepository, PaymentRepository paymentRepository) {
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Account acc1 = new Account("111", Currency.EUR, BigDecimal.valueOf(1000));
        Account acc2 = new Account("222", Currency.EUR, BigDecimal.valueOf(100));
        Account acc3 = new Account("333", Currency.EUR, BigDecimal.valueOf(0));
        accountRepository.save(acc1);
        accountRepository.save(acc2);
        accountRepository.save(acc3);
    }

}
