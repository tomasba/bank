package com.bank.demo.sync;

import org.hibernate.validator.internal.util.ConcurrentReferenceHashMap;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentMap;

@Service
public class IbanMutexFactory {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    private static final ConcurrentReferenceHashMap.ReferenceType DEFAULT_REFERENCE_TYPE =
            ConcurrentReferenceHashMap.ReferenceType.WEAK;

    private final ConcurrentMap<String, IbanMutex> map;

    public IbanMutexFactory() {
        this.map = new ConcurrentReferenceHashMap<>(DEFAULT_INITIAL_CAPACITY,
                DEFAULT_LOAD_FACTOR,
                DEFAULT_CONCURRENCY_LEVEL,
                DEFAULT_REFERENCE_TYPE,
                DEFAULT_REFERENCE_TYPE,
                null);
    }

    public IbanMutex getMutex(String iban) {
        return this.map.computeIfAbsent(iban, IbanMutex::new);
    }

}