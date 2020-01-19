package com.bank.demo.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "TransactionDeposit")
@DiscriminatorValue("TransactionDeposit")
public class TransactionDeposit extends Transaction {
}
