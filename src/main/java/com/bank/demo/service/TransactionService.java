package com.bank.demo.service;

import com.bank.demo.dao.TransactionRepository;
import com.bank.demo.domain.entity.Transaction;
import com.bank.demo.domain.model.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public <T extends Transaction> T saveTransaction(T t) {
        return transactionRepository.save(t);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void transfer(PaymentRequest paymentDetails) {
//        Account accountFrom = accountService.findAccount(paymentDetails.getFromIban());
//        Account accountTo = accountService.findAccount(paymentDetails.getToIban());
    }

}
