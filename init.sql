
CREATE DATABASE IF NOT EXISTS dbChess;


create table t_user
(
    username varchar(55)  not null
        primary key,
    password varchar(255) null,
    wins int null,
    losses int null,
    draws int null
);




