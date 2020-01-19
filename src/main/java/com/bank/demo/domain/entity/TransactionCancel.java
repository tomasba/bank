package com.bank.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "TransactionCancel")
@DiscriminatorValue("cancel")
@JsonTypeName("cancel")
public class TransactionCancel extends Transaction {
}
