package com.bank.demo.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@SequenceGenerator(name = "transaction_id_gen", sequenceName = "transaction_seq", allocationSize = 1)
public abstract class Transaction {

    public Transaction() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_gen")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", unique = true, updatable = false, nullable = false)
    protected Long id;

    @Column(nullable = false)
    protected String uid;

    @Column(nullable = false)
    protected BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}