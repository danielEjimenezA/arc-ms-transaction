CREATE TABLE person
(
    id      varchar not null
        constraint persona_pk
            primary key,
    name    varchar,
    gender  varchar,
    age     integer,
    address varchar,
    phone   varchar
);

CREATE TABLE client
(
    client_id bigint  not null
        constraint client_pk
            primary key,
    password  varchar,
    status    bool,
    person_id varchar not null unique
        constraint client_person_id_fk
            references person
);

CREATE TABLE account
(
    account_id     bigint
        constraint account_pk
            primary key,
    client_id      bigint unique
        constraint account_client_client_id_fk
            references client,
    account_number bigint,
    account_type   varchar,
    initial_amount float8,
    status         bool
);

CREATE TABLE transac
(
    transac_id       bigint
        constraint transac_pk
            primary key,
    account_id       bigint
        constraint transac_account_account_id_fk
            references account,
    date             date not null,
    transaction_type varchar,
    value            float8,
    balance          float8
);