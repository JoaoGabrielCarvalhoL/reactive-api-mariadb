--CREATE DATABASE IF NOT EXISTS beers_reactive;

CREATE TABLE if NOT EXISTS tb_beer
(
    id             bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    beer_name      varchar(255),
    beer_style     varchar (255),
    upc            varchar (25),
    version        integer,
    quantity_on_hands integer,
    unit_price          decimal,
    created_date   date,
    update_date date
);
