-- liquibase formatted sql
-- changeset changelog:2024.05.v6
CREATE TABLE transaction
(
    id         UUID                        NOT NULL,
    from_id     UUID,
    to_id       UUID,
    service_id UUID,
    type_id    UUID,
    date_time  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    value      DECIMAL                     NOT NULL,
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_FROM FOREIGN KEY (from_id) REFERENCES users (id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_SERVICE FOREIGN KEY (service_id) REFERENCES services (id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_TO FOREIGN KEY (to_id) REFERENCES users (id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_TYPE FOREIGN KEY (type_id) REFERENCES transaction_type (id);