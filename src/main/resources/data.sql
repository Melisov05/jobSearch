
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

insert into resumes (id, applicant_id, name, category_id, salary, is_active, created_date, updated_date)
values (1, 1, 'резюме_соискателя_1', 1, 50000.0, true, now(), now());

insert into resumes (id, applicant_id, name, category_id, salary, is_active, created_date, updated_date)
values (2, 1, 'резюме_соискателя_2', 2, 60000.0, true, now(), now());

create table vacancies (
    id int primary key,
    employer_id int,
    name varchar(255),
    category_id int,
    salary double,
    is_active boolean,
    created_date timestamp,
    updated_date timestamp,
    foreign key (employer_id) references users(id)
);


insert into vacancies (id, employer_id, name, category_id, salary, is_active, created_date, updated_date)
values (1, 2, 'Младший волшебник', 1, 55000.0, true, now(), now());

insert into vacancies (id, employer_id, name, category_id, salary, is_active, created_date, updated_date)
values (2, 2, 'Старший волшебник', 2, 70000.0, true, now(), now());

create table applications (
    id int primary key,
    resume_id int,
    vacancy_id int,
    foreign key (resume_id) references resumes(id),
    foreign key (vacancy_id) references vacancies(id)
);

insert into applications (id, resume_id, vacancy_id, applied_date)
values (1, 1, 1, now());









