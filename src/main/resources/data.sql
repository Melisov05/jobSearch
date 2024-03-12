
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


insert into users (id, name, surname, age, email, password, phone_number, avatar, account_type)
values (1, 'гарри', 'поттер', 25, 'soiskatel@example.com', 'password1', '+123456789', 'avatar1.jpg', 'соискатель');


insert into users (id, name, surname, age, email, password, phone_number, avatar, account_type)
values (2, 'дамблдор', 'профессор', 30, 'rabotodatel@example.com', 'password2', '+987654321', 'avatar2.jpg', 'работодатель');


create table resumes (
    id int primary key,
    applicant_id int,
    name varchar(255),
    category_id int,
    salary double,
    is_active boolean,
    created_date timestamp,
    updated_date timestamp,
    foreign key (applicant_id) references users(id)
);


