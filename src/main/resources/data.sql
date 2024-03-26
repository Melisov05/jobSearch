create table if not exists users (
    id int primary key auto_increment,
    name varchar(255),
    surname varchar(255),
    age int,
    email varchar(255) unique,
    password varchar(255),
    phone_number varchar(15),
    avatar varchar(255),
    account_type varchar(20)
);


insert into users (name, surname, age, email, password, phone_number, avatar, account_type)
values ('Гарри', 'Поттер', 25, 'soiskatel@example.com', 'password1', '+123456789', 'avatar1.jpg', 'соискатель');


insert into users (name, surname, age, email, password, phone_number, avatar, account_type)
values ('Дамблдор', 'Профессор', 30, 'rabotodatel@example.com', 'password2', '+987654321', 'avatar2.jpg', 'работодатель');


create table if not exists resumes (
    id int primary key auto_increment,
    applicant_id int,
    name varchar(255),
    category_id int,
    salary double,
    is_active boolean,
    created_date timestamp,
    updated_date timestamp,
    foreign key (applicant_id) references users(id)
);

insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, updated_date)
values (1, 'резюме_соискателя_1', 1, 50000.0, true, now(), now());

insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, updated_date)
values (1, 'резюме_соискателя_2', 2, 60000.0, true, now(), now());


create table if not exists categories(
        id int primary key auto_increment,
        name varchar(255),
        parent_id int
);


insert into categories (name, parent_id)
values ('category1', 0),
       ('category2', 1);


create table if not exists vacancies (
    id int primary key auto_increment,
    name varchar(255),
    description varchar(255),
    category_id int,
    salary double,
    exp_from int,
    exp_to int,
    is_active boolean,
    author_id int,
    created_date timestamp,
    updated_time timestamp,
    foreign key (author_id) references users(id),
    foreign key (category_id) references categories(id)
);

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, updated_time)
values ('Младший волшебник', 'Описание вакансии 1', 1, 55000.0, 1, 5, true, 2, now(), now()),
       ('Старший волшебник', 'Описание вакансии 2', 2, 70000.0, 3, 8, true, 2, now(), now());

create table if not exists applications(
    id int primary key auto_increment,
    resume_id int,
    vacancy_id int,
    foreign key (resume_id) references resumes(id),
    foreign key (vacancy_id) references vacancies(id)
);

insert into applications (resume_id, vacancy_id)
values (1, 1);


create table if not exists categories(
    id int primary key auto_increment,
    name varchar(255),
    parent_id int
);


insert into categories (name, parent_id)
values ('category1', 0),
       ('category2', 1);


create table if not exists contact_types (
    id int primary key auto_increment,
    type varchar(255)
);


insert into contact_types (type)
values ('Email'),
       ('Phone');


create table if not exists contact_info (
    id int primary key auto_increment,
    type_id int,
    resume_id int,
    value varchar(255),
    foreign key (type_id) references contact_types(id),
    foreign key (resume_id) references resumes(id)
);



insert into contact_info (type_id, resume_id, value)
values (1, 1, 'applicant@example.com'),
       (2, 1, '+123456789');


create table if not exists education_info (
    id int primary key auto_increment,
    resume_id int,
    institution varchar(255),
    program varchar(255),
    start_date date,
    end_date date,
    degree varchar(255),
    foreign key (resume_id) references resumes(id)
);


insert into education_info (resume_id, institution, program, start_date, end_date, degree)
values (1, 'University A', 'Computer Science', '2020-01-01', '2024-01-01', 'Bachelor'),
       (2, 'University B', 'Business Administration', '2019-01-01', '2023-01-01', 'Bachelor');


create table if not exists messages (
    id int primary key auto_increment,
    responded_applicant_id int,
    message_text varchar(255),
    timestamp timestamp,
    foreign key (responded_applicant_id) references responded_applicants(id)
);


insert into messages (responded_applicant_id, message_text, timestamp)
values (1, 'Sample message 1', now()),
       (2, 'Sample message 2', now());



create table if not exists responded_applicants (
    id int primary key auto_increment,
    resume_id int,
    vacancy_id int,
    confirmation boolean,
    foreign key (resume_id) references resumes(id),
    foreign key (vacancy_id) references vacancies(id)
);

insert into responded_applicants (resume_id, vacancy_id, confirmation)
values (1, 1, true),
       (2, 2, false);









