package com.bank.demo.dao;

import com.bank.demo.domain.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository <T extends Transaction> extends CrudRepository<T, Long> {



}
