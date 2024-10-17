create table users
(
    id BIGSERIAL primary key ,
    first_name varchar(64) not null ,
    last_name varchar(64) not null ,
    email varchar(64) not null ,
    password varchar(64) not null ,
    role text
);