package com.bank.demo.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionValidator {

    void validateAmountPositive(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 1) {
            throw new RuntimeException("Transactional amount of money should be positive number.");
        }
    }

    void validateCreditAccountHasSufficientFunds(BigDecimal creditorBalance, BigDecimal transferCreditAmount) {
        if (creditorBalance.compareTo(transferCreditAmount) < 0) {
            throw new RuntimeException("Creditor does not have enough funds to complete operation.");
        }
    }

    void validateCreditorAndDebtorIbansDiffer(String creditorIban, String debtorIban) {
        if (StringUtils.equals(creditorIban, debtorIban)) {
            throw new RuntimeException("Creditor and debtor IBANs must not be the same.");
        }
    }
}
