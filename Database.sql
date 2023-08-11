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

create table authorities
(
    id      bigint not null auto_increment,
    name    varchar(100),
    user_id bigint,
    primary key (id),
    foreign key fk_users_authorities (user_id) references users (id)
) engine = InnoDB;

drop table authorities;
desc authorities;
select * from users;
select * from authorities;

