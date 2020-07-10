create table transaction_schema.t_wallet(
    id serial primary key,
    amount decimal
);

INSERT INTO transaction_schema.t_wallet(id, amount)
VALUES (1, 0),
        (2, 0);

INSERT INTO transaction_schema.t_user (name, username, iban, cnp, walletid)
VALUES ('Delia Tristu', 'deliatristu', 'RO87RZBR9496777641988849', '2951009800533', 1),
        ('Ciprian Ionascu', 'ciprianionascu', 'RO52RZBR9181288511154499', '1951009800533' , 2);

alter table transaction_schema.t_wallet
    owner to postgres;
