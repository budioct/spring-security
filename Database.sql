create database belajar_spring_security;

show databases;

use belajar_spring_security;

show tables;

show create database belajar_spring_security;

create table users
(
    id       bigint       not null auto_increment,
    username varchar(100) not null,
    password varchar(100) not null,
    primary key (id)
) engine = InnoDB;

drop table users;

desc users;

select * from users;

