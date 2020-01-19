package com.bank.demo.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "TransactionCancel")
@DiscriminatorValue("TransactionCancel")
public class TransactionCancel extends Transaction {
}
