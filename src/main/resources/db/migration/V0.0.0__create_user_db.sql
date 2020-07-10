CREATE SCHEMA IF NOT EXISTS transaction_schema;

create table transaction_schema.t_user(
    id serial primary key,
    name varchar(20),
    username varchar(20),
    iban varchar(50),
    cnp varchar(50),
    walletid integer
);

alter table transaction_schema.t_user
    owner to postgres;

