CREATE TABLE IF NOT EXISTS customer_info
(
    id                   bigint unsigned AUTO_INCREMENT PRIMARY KEY,
    customer_name        varchar(32) NOT NULL,
    password             varchar(32) NOT NULL DEFAULT '12345678',
    customer_name_crypto varchar(64) NULL,
    pwd_crypto           varchar(64) NULL
);

insert into customer_info (customer_name, password)
values ('gold1', 'pw1');
insert into customer_info (customer_name, password)
values ('gold2', 'pw2');
insert into customer_info (customer_name, password)
values ('gold3', 'pw3');
insert into customer_info (customer_name, password)
values ('gold4', 'pw4');
insert into customer_info (customer_name, password)
values ('gold5', 'pw5');