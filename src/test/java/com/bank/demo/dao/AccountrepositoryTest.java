package com.bank.demo.dao;

import com.bank.demo.domain.Currency;
import com.bank.demo.domain.Operation;
import com.bank.demo.domain.entity.Account;
import com.bank.demo.domain.entity.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountrepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldFindAccountByIban() {
        // given
        Account acc = new Account("iban123", Currency.EUR, BigDecimal.valueOf(500));
        entityManager.persist(acc);
        entityManager.flush();

        // when
        Account result =  accountRepository.findAccountByIban("iban123");

        // then
        assertThat(result, notNullValue());
    }

    @Test
    public void shouldFindAccountByTransactionUid() {
        // given
        Account acc123 = new Account("iban123", Currency.EUR, BigDecimal.valueOf(500));
        Account acc321 = new Account("iban321", Currency.EUR, BigDecimal.valueOf(50));

        Transaction tr123 = new Transaction();
        tr123.setUid("transaction123");
        tr123.setOperation(Operation.CREDIT);
        tr123.setAmount(BigDecimal.valueOf(30));
        tr123.setAccount(acc123);
        acc123.getTransactions().add(tr123);

        Transaction tr321 = new Transaction();
        tr321.setUid("transaction123");
        tr321.setOperation(Operation.DEBIT);
        tr321.setAmount(BigDecimal.valueOf(30));
        tr321.setAccount(acc123);
        acc321.getTransactions().add(tr321);

        entityManager.persist(acc123);
        entityManager.persist(acc321);
        entityManager.flush();

        // when
        Account creditor =  accountRepository.findCreditorAccountByTransaction("transaction123");
        Account debtor =  accountRepository.findDebtorAccountByTransaction("transaction123");

        // then
        assertThat(creditor, notNullValue());
        assertThat(creditor.getIban(), equalTo("iban123"));
        assertThat(debtor, notNullValue());
        assertThat(debtor.getIban(), equalTo("iban321"));
    }
}
