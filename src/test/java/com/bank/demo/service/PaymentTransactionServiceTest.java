package com.bank.demo.service;

import com.bank.demo.dao.AccountRepository;
import com.bank.demo.dao.TransactionRepository;
import com.bank.demo.domain.Currency;
import com.bank.demo.domain.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentTransactionServiceTest {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionValidator transactionValidator;

    // TODO !!!finish test cases!!!
    @Test
    public void shouldExecutePaymentTransaction() {
        // given
        given(accountRepository.findAccountByIban("fromIban")).willReturn(createCreditorAccount());
        given(accountRepository.findAccountByIban("toIban")).willReturn(createDebtorAccount());

        // when
        paymentTransactionService.transfer("fromIban", "toIban", BigDecimal.valueOf(45));

        // then
        verify(transactionValidator).validateCreditorAndDebtorIbansDiffer("fromIban", "toIban");
        verify(transactionValidator).validateAmountPositive(BigDecimal.valueOf(45));

    }

    private Account createDebtorAccount() {
        return new Account("toIban", Currency.EUR, BigDecimal.valueOf(10));
    }

    private Account createCreditorAccount() {
        return new Account("fromIban", Currency.EUR, BigDecimal.valueOf(100));
    }


}
