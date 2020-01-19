package com.bank.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "TransactionDeposit")
@DiscriminatorValue("deposit")
@JsonTypeName("deposit")
public class TransactionDeposit extends Transaction {
}
