
CREATE DATABASE IF NOT EXISTS dbChess;


create table t_user
(
    username varchar(55)  not null
        primary key,
    password varchar(255) null
);



create table t_highscore
(
    highscore_id int auto_increment
        primary key,
    name         varchar(55) not null,
    points       int         null,
    constraint t_highscore_t_user_username_fk
        foreign key (name) references t_user (username)
);

