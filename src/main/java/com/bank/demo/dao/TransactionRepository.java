package com.bank.demo.dao;

import com.bank.demo.domain.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository  extends CrudRepository<Transaction, Long> {

    @Query("from Transaction tr left join fetch tr.account where tr.uid = :uid")
    List<Transaction> findByUid(@Param("uid") String uid);

    @Query("from Transaction tr where tr.operation = 'CANCEL' and tr.uid = :uid")
    List<Transaction> findCancelled(@Param("uid") String uid);

}
