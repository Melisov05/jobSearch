
create table users (
    id int primary key,
    name varchar(255),
    surname varchar(255),
    age int,
    email varchar(255) unique,
    password varchar(255),
    phone_number varchar(15),
    avatar varchar(255),
    account_type varchar(20)
);


