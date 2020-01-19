package com.bank.demo.service;

import com.bank.demo.dao.TransactionRepository;
import com.bank.demo.domain.entity.Transaction;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public <T extends Transaction> T saveTransaction(T t) {
        return (T) transactionRepository.save(t);
    }

    public List<Transaction> findAllTransactions() {
        return IterableUtils.toList(transactionRepository.findAll());
    }

}