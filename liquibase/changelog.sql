-- changeset liquibase:1
CREATE TABLE klient (
    id serial not null primary key,
    name varchar(255),
    adres_email varchar(255)
);

-- changeset liquibase:2
CREATE TABLE koszyk (
    id serial not null primary key,
    klient_id int not null,
    name varchar(255),

    FOREIGN KEY (klient_id) REFERENCES klient(id)
);

-- changeset liquibase:3
CREATE TABLE produkt (
    id serial not null primary key,
    name varchar(255),
    jednostka_miary varchar(255)
);

-- changeset liquibase:4
CREATE TABLE pozycja (
    id serial not null primary key,
    ilosc_produkt int not null,
    koszyk_id int not null,
    produkt_id int not null,

    FOREIGN KEY (koszyk_id) REFERENCES koszyk(id),
    FOREIGN KEY (produkt_id) REFERENCES produkt(id)
);

-- changeset liquibase:5
ALTER TABLE produkt ADD opis varchar(255);

-- changeset liquibase:6
CREATE TABLE komentarz (
    id serial not null primary key,
    produkt_id int not null,
    autor varchar(255),
    FOREIGN KEY (produkt_id) REFERENCES produkt(id)
);

-- changeset liquibase:7
ALTER TABLE komentarz ADD tresc varchar(255);
