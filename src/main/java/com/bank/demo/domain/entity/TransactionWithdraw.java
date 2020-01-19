package com.bank.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "TransactionWithdraw")
@DiscriminatorValue("withdraw")
@JsonTypeName("withdraw")
public class TransactionWithdraw extends Transaction {
}
