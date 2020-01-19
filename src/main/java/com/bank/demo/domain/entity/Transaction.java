package com.bank.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@SequenceGenerator(name = "transaction_id_gen", sequenceName = "transaction_seq", allocationSize = 1)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TransactionCancel.class, name = "cancel"),
        @JsonSubTypes.Type(value = TransactionDeposit.class, name = "deposit"),
        @JsonSubTypes.Type(value = TransactionWithdraw.class, name = "withdraw")
})
public abstract class Transaction {

    public Transaction() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_gen")
    @Column(name = "transaction_id", unique = true, updatable = false, nullable = false)
    protected Long id;

    @Column(nullable = false)
    protected String uid;

    @Column(nullable = false)
    protected BigDecimal amount;

    @Column(name = "account_id", insertable = false, updatable = false)
    protected String accountId;

    @Column(insertable = false, updatable = false)
    protected String type;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    @JsonIgnore
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}