package com.bank.demo.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountSynchronizer {

    private final IbanMutexFactory ibanMutexFactory;

    @Autowired
    public AccountSynchronizer(IbanMutexFactory ibanMutexFactory) {
        this.ibanMutexFactory = ibanMutexFactory;
    }

    public void execute(String iban, Runnable runnable) {
        IbanMutex mutex = ibanMutexFactory.getMutex(iban);
        synchronized (mutex) {
            runnable.run();
        }
    }

    public void execute(String fromIban, String toIban, Runnable runnable) {
        IbanMutex firstMutex = ibanMutexFactory.getMutex(fromIban);
        IbanMutex secondMutex = ibanMutexFactory.getMutex(toIban);

        if (firstMutex.compareTo(secondMutex) > 0) {
            IbanMutex tmp = firstMutex;
            firstMutex = secondMutex;
            secondMutex = tmp;
        }

        synchronized (firstMutex) {
            synchronized (secondMutex) {
                runnable.run();
            }
        }

    }

}