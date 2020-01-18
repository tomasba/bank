package com.bank.demo.domain.model;

import com.bank.demo.domain.Currency;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class NewAccountRequest {

    private String iban;
    private Currency currency;
    private BigDecimal balance = BigDecimal.ZERO;

    public NewAccountRequest(@NotNull(message = "Account iban is required") String iban,
                             @NotNull(message = "Account currency is required.") Currency currency,
                             BigDecimal balance) {
        this.iban = iban;
        this.currency = currency;
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
