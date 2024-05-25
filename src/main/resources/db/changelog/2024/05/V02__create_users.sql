-- liquibase formatted sql
-- changeset changelog:2024.05.v2
CREATE TABLE users
(
    id           UUID        NOT NULL,
    name         TEXT,
    password     TEXT        NOT NULL,
    phone_number VARCHAR(55),
    number VARCHAR(6) unique ,
    account real,
    role_id      UUID        REFERENCES role (id),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

--пароль: string
INSERT INTO users (id, name, password, phone_number, number, role_id, account)
VALUES ('9aff255b-fd68-4a8c-9953-76465b8c2f87', 'string', '$2a$12$VdKhXm/G/eeEjojRiwFrdeDwKW/waaTiSEaVOL8dSdvALKh0KvhMW', '555555555', '000000', '5d16e497-14b2-4ff8-aad0-f84d00085389', 0);