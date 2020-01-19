package com.bank.demo.controller;

import com.bank.demo.domain.model.PaymentRequest;
import com.bank.demo.service.PaymentTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("payments")
public class PaymentController {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody PaymentRequest paymentDetails) {
        paymentTransactionService.transfer(paymentDetails.getFromIban(), paymentDetails.getToIban(),
                paymentDetails.getAmount());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
