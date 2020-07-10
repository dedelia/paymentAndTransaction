create table transaction_schema.t_transaction(
    id serial primary key,
    transaction_type varchar(50),
    payercnp varchar(50),
    payeriban varchar(50),
    payeecnp varchar(50),
    payeeiban varchar(50),
    description varchar(50),
    amount decimal
);

alter table transaction_schema.t_transaction
    owner to postgres;

