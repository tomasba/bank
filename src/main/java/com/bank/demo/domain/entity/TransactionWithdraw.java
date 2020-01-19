package com.bank.demo.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "TransactionWithdraw")
@DiscriminatorValue("TransactionWithdraw")
public class TransactionWithdraw extends Transaction {
}
