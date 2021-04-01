CREATE MEMORY TABLE PUBLIC.BANK
(
    id   BINARY(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE MEMORY TABLE PUBLIC.CLIENT
(
    id              BINARY(255) NOT NULL PRIMARY KEY,
    surname         VARCHAR(255),
    name            VARCHAR(255),
    patronymic      VARCHAR(255),
    email           VARCHAR(255),
    phone           VARCHAR(255),
    passport_number VARCHAR(255),
    bank_id         BINARY(255),
    CONSTRAINT CLIENT_BANK FOREIGN KEY (bank_id) REFERENCES PUBLIC.BANK (id)
);

CREATE MEMORY TABLE PUBLIC.CREDIT
(
    id           BINARY NOT NULL PRIMARY KEY,
    limit        NUMERIC(24, 6),
    percent_rate NUMERIC(24, 6),
    bank_id      BINARY(255),
    CONSTRAINT CREDIT_BANK FOREIGN KEY (bank_id) REFERENCES PUBLIC.BANK (id)
);

CREATE MEMORY TABLE PUBLIC.CREDIT_OFFER
(
    id                  BINARY NOT NULL PRIMARY KEY,
    overpayment         NUMERIC(24, 2),
    credit_amount       NUMERIC(24, 2),
    client_id           BINARY(255),
    credit_id           BINARY(255),
    payment_schedule_id BINARY(255) NOT NULL PRIMARY KEY,
    CONSTRAINT CREDIT_OFFER_CLIENT FOREIGN KEY (client_id) REFERENCES PUBLIC.CLIENT (id),
    CONSTRAINT CREDIT_OFFER_CREDIT FOREIGN KEY (credit_id) REFERENCES PUBLIC.BANK (id),
);

CREATE MEMORY TABLE PUBLIC.PAYMENT_SCHEDULE(
   id BINARY NOT NULL PRIMARY KEY

);

CREATE MEMORY TABLE PUBLIC.PAYMENT(
    id BINARY NOT NULL PRIMARY KEY,
    date TIMESTAMP,
    amount_of_payment NUMERIC(24,2),
    principal_repayment_amount NUMERIC(24,2),
    percent_repayment_amount NUMERIC(24,2),
    payment_schedule_id BINARY(255),
    CONSTRAINT PAYMENT_PAYMENT_SCHEDULE FOREIGN KEY (payment_schedule_id) REFERENCES PUBLIC.PAYMENT_SCHEDULE (id)
);

