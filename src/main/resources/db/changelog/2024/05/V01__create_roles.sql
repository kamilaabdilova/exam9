-- liquibase formatted sql
-- changeset changelog:2024.05.v1
CREATE TABLE role
(
    id   UUID         NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

INSERT INTO public.role (id, name) VALUES ('5d16e497-14b2-4ff8-aad0-f84d00085389', 'User');
