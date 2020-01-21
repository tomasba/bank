package com.bank.demo.domain.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PaymentRequest {

    private final String fromIban;
    private final String toIban;
    private final BigDecimal amount;

    public PaymentRequest(@NotNull(message = "Creditor iban is required") String fromIban,
                          @NotNull(message = "Debtor iban is required") String toIban,
                          @NotNull(message = "Transfer amount must be specified")
                                  @Positive(message = "Transfer amount must be positive value")
                                  BigDecimal amount) {
        this.fromIban = fromIban;
        this.toIban = toIban;
        this.amount = amount;
    }

    public String getFromIban() {
        return fromIban;
    }

    public String getToIban() {
        return toIban;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
