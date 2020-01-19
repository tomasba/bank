package com.bank.demo.sync;

import java.util.Objects;

public class IbanMutex implements Comparable<IbanMutex> {

    private final String iban;

    public IbanMutex(String iban) {
        this.iban = iban;
    }

    public static IbanMutex of(String iban) {
        return new IbanMutex(iban);
    }

    @Override
    public int compareTo(IbanMutex o) {
        return this.iban.compareTo(o.getIban());
    }

    public String getIban() {
        return iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IbanMutex ibanMutex = (IbanMutex) o;
        return Objects.equals(iban, ibanMutex.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }
}
