package com.bank.demo.domain.entity;

import com.bank.demo.domain.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@SequenceGenerator(name = "account_id_gen", sequenceName = "account_seq", allocationSize = 1)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_gen")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String iban;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    public Account() {
    }

    public Account(String iban, Currency currency, BigDecimal balance) {
        this.iban = iban;
        this.currency = currency;
        this.balance = balance;
    }

    public Account(String iban, Currency currency) {
        this.iban = iban;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                '}';
    }

    public Long getId() {
        return id;
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
