package com.bank.demo.domain.entity;

import com.bank.demo.domain.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@SequenceGenerator(name = "account_id_gen", sequenceName = "account_seq", allocationSize = 1)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_gen")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String iban;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    //cascade = {CascadeType.PERSIST},fetch= FetchType.EAGER,
    @OneToMany(mappedBy = "id", cascade = {CascadeType.PERSIST})
    private Set<TransactionCancel> cancellTransactions;

    @OneToMany(mappedBy = "id", cascade = {CascadeType.PERSIST})
    private Set<TransactionWithdraw> withdrawTransactions;

    @OneToMany(mappedBy = "id", cascade = {CascadeType.PERSIST})
    private Set<TransactionDeposit> depositTransactions;

    public Account() {
    }

    public Account(String iban, Currency currency, BigDecimal balance) {
        this.iban = iban;
        this.currency = currency;
        this.balance = balance;
    }

    public void addCancellTransactions(TransactionCancel transactionCancel) {
        if (cancellTransactions == null) {
            cancellTransactions = new HashSet<>();
        }
        cancellTransactions.add(transactionCancel);
        transactionCancel.setAccount(this);
    }

    public void addWithdrawTransactions(TransactionWithdraw transactionWithdraw) {
        if (withdrawTransactions == null) {
            withdrawTransactions = new HashSet<>();
        }
        withdrawTransactions.add(transactionWithdraw);
        transactionWithdraw.setAccount(this);
    }

    public void addDepositTransactions(TransactionDeposit transactionDeposit) {
        if (depositTransactions == null) {
            depositTransactions = new HashSet<>();
        }
        depositTransactions.add(transactionDeposit);
        transactionDeposit.setAccount(this);
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Set<TransactionCancel> getCancellTransactions() {
        return cancellTransactions;
    }

    public void setCancellTransactions(Set<TransactionCancel> cancellTransactions) {
        this.cancellTransactions = cancellTransactions;
    }

    public Set<TransactionWithdraw> getWithdrawTransactions() {
        return withdrawTransactions;
    }

    public void setWithdrawTransactions(Set<TransactionWithdraw> withdrawTransactions) {
        this.withdrawTransactions = withdrawTransactions;
    }

    public Set<TransactionDeposit> getDepositTransactions() {
        return depositTransactions;
    }

    public void setDepositTransactions(Set<TransactionDeposit> depositTransactions) {
        this.depositTransactions = depositTransactions;
    }
}
