-- liquibase formatted sql
-- changeset changelog:2024.05.v3
CREATE TABLE provider
(
    id      UUID NOT NULL,
    name    TEXT,
    account DECIMAL,
    CONSTRAINT pk_provider PRIMARY KEY (id)
);
INSERT INTO public.provider (id, name, account)
VALUES ('61becd97-2dee-4a81-830e-8b42e6760663', 'Megacom', 10000);

INSERT INTO public.provider (id, name, account)
VALUES ('61becd97-2dee-4a81-830e-8b42e6760665', 'Megaline', 10000);

INSERT INTO public.provider (id, name, account)
VALUES ('61becd97-2dee-4a81-830e-8b42e6760669', 'Ak Tulpar', 10000);

