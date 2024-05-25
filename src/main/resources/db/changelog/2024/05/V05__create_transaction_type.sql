-- liquibase formatted sql
-- changeset changelog:2024.05.v5
CREATE TABLE transaction_type
(
    id   UUID         NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_transactiontype PRIMARY KEY (id)
);

ALTER TABLE transaction_type
    ADD CONSTRAINT uc_transactiontype_name UNIQUE (name);

INSERT INTO public.transaction_type (id, name)
VALUES ('ce737a47-0155-4e72-b9f1-aacc6c862a9d', 'increase');

INSERT INTO public.transaction_type (id, name)
VALUES ('ce737a47-0155-4e72-b9f1-aacc6c862a90', 'transfer');

INSERT INTO public.transaction_type (id, name)
VALUES ('ce737a47-0155-4e72-b9f1-aacc6c862a97', 'withdraw');

INSERT INTO public.transaction_type (id, name)
VALUES ('ce737a47-0155-4e72-b9f1-aacc6c86269d', 'service');

