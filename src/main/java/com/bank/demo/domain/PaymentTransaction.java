package com.bank.demo.domain;

import java.math.BigDecimal;

public class PaymentTransaction {

    private String creditor_iban;
    private String debtor_iban;
    private PaymentType paymentType;
    BigDecimal amount;

}
