package com.bank.demo.dao;

import com.bank.demo.domain.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Account, Long> {

    Account findAccountByIban(String iban);

}
