CREATE TABLE accounts
(
    id          integer PRIMARY KEY,
    iban        varchar(100) NOT NULL UNIQUE,
    currency    varchar(3) NOT NULL,
    balance     numeric(8,2) NOT NULL
);

CREATE SEQUENCE account_seq;