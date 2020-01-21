package com.bank.demo.dao;

import com.bank.demo.domain.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findAccountByIban(String iban);

    @Query(value = "SELECT a.iban FROM ACCOUNTS a join FINANCIAL_TRANSACTIONS ft on a.id = ft.account_id and " +
            "ft.operation = 'CREDIT' where ft.uid = :uid", nativeQuery = true)
    String findCreditorAccountUidByTransaction(@Param("uid") String uid);

    @Query(value = "SELECT a.iban FROM ACCOUNTS a join FINANCIAL_TRANSACTIONS ft on a.id = ft.account_id and " +
            "ft.operation = 'DEBIT' where ft.uid = :uid", nativeQuery = true)
    String findDebtorAccountUidByTransaction(@Param("uid") String uid);

    @Query("from Account acc left join fetch acc.transactions t where t.operation = 'CREDIT' and t.uid = :uid")
    Account findCreditorAccountByTransaction(@Param("uid") String uid);

    @Query("from Account acc left join fetch acc.transactions t where t.operation = 'DEBIT' and t.uid = :uid")
    Account findDebtorAccountByTransaction(@Param("uid") String uid);

    @Query("from Account acc left join fetch acc.transactions t where t.operation = 'CANCEL' and t.uid = :uid")
    List<Account> findCancellationsByUid(@Param("uid") String uid);
}
