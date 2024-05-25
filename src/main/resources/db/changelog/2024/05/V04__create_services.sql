-- liquibase formatted sql
-- changeset changelog:2024.05.v4
CREATE TABLE services
(
    id          UUID NOT NULL,
    provider_id UUID,
    name        VARCHAR(255),
    cost        DECIMAL,
    CONSTRAINT pk_services PRIMARY KEY (id)
);

ALTER TABLE services
    ADD CONSTRAINT FK_SERVICES_ON_PROVIDER FOREIGN KEY (provider_id) REFERENCES provider (id);

INSERT INTO public.services (id, provider_id, name, cost)
VALUES ('0ffb2b2b-a12e-4515-bdd9-068700b5368b', '61becd97-2dee-4a81-830e-8b42e6760663', 'Пополнить баланс', null);

INSERT INTO public.services (id, provider_id, name, cost)
VALUES ('0ffb2b2b-a12e-4515-bdd9-068700b53688', '61becd97-2dee-4a81-830e-8b42e6760665',
        'Оплата за интернет(тариф турбо)', 800);

INSERT INTO public.services (id, provider_id, name, cost)
VALUES ('0ffb2b2b-a12e-4515-bdd9-068700b53681', '61becd97-2dee-4a81-830e-8b42e6760669', 'Оплата за электроэнергию',
        null);

