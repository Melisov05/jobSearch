
INSERT INTO categories (name, parent_id)
VALUES
    ('IT', NULL),
    ('Engineering', NULL);

INSERT into CATEGORIES(name, PARENT_ID)
values ('Software Development',
        (SELECT id FROM categories WHERE name = 'IT' limit 1)), -- Child of 'IT'
       ('Network Engineering', (SELECT id FROM categories WHERE name = 'IT')), -- Child of 'IT'
       ('Civil Engineering', (SELECT id FROM categories WHERE name = 'Engineering')), -- Child of 'Engineering'
       ('Electrical Engineering', (SELECT id FROM categories WHERE name = 'Engineering')); -- Child of 'Engineering'

INSERT INTO users (name, surname, email, password, age, phone_number, avatar, account_type)
VALUES
    ('John', 'Doe', 'john.doe@example.com', 'password123', 30, '123-456-7890', 'avatar1.jpg', 'candidate'),
    ('Jane', 'Smith', 'jane.smith@example.com', 'letmein', 25, '987-654-3210', 'avatar2.jpg', 'employer');

INSERT INTO resumes (name, applicant_id, category_id, salary, is_active, created_date, updated_date)
VALUES
    ('John Doe Resume', (SELECT id FROM users WHERE email = 'john.doe@example.com'),
     (SELECT id FROM categories WHERE name = 'Software Development'), 70000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('Jane Smith Resume', (SELECT id FROM users WHERE email = 'jane.smith@example.com'),
     (SELECT id FROM categories WHERE name = 'Civil Engineering'), 80000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, updated_time)
VALUES
    ('Software Engineer', 'Looking for a skilled software engineer',
     (SELECT id FROM categories WHERE name = 'Software Development'), 90000.00, 3, 5, true,
     (SELECT id FROM users WHERE email = 'jane.smith@example.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('Civil Engineer', 'Seeking a civil engineer for infrastructure projects',
     (SELECT id FROM categories WHERE name = 'Civil Engineering'), 95000.00, 5, 8, true,
     (SELECT id FROM users WHERE email = 'jane.smith@example.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES
    ((SELECT id FROM resumes WHERE name = 'John Doe Resume'),
     (SELECT id FROM vacancies WHERE name = 'Software Engineer'), true),

    ((SELECT id FROM resumes WHERE name = 'Jane Smith Resume'),
     (SELECT id FROM vacancies WHERE name = 'Civil Engineer'), false);

INSERT INTO messages (responded_applicants, content, timestamp)
VALUES
    ((SELECT id FROM responded_applicants
      WHERE resume_id = (SELECT id FROM resumes WHERE name = 'John Doe Resume')),
     'Hello John, we are interested in your profile', CURRENT_TIMESTAMP),

    ((SELECT id FROM responded_applicants
      WHERE resume_id = (SELECT id FROM resumes WHERE name = 'Jane Smith Resume')),
     'Thank you for applying, Jane. We will review your resume.', CURRENT_TIMESTAMP);

INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree)
VALUES
    ((SELECT id FROM resumes WHERE name = 'John Doe Resume'), 'University of XYZ',
     'Computer Science', '2015-09-01', '2019-06-01', 'Bachelor'),

    ((SELECT id FROM resumes WHERE name = 'Jane Smith Resume'), 'ABC College',
     'Civil Engineering', '2016-09-01', '2020-06-01', 'Bachelor');

INSERT INTO work_experience_info (years, company_name, position, responsibilities, resume_id)
VALUES
    (3, 'TechCorp', 'Software Engineer', 'Developed web applications',
     (SELECT id FROM resumes WHERE name = 'John Doe Resume')),

    (4, 'Construction Co.', 'Civil Engineer', 'Managed construction projects',
     (SELECT id FROM resumes WHERE name = 'Jane Smith Resume'));

INSERT INTO contact_types (type)
VALUES
    ('Phone'),
    ('Telegram'),
    ('LinkedIn'),
    ('Facebook'),
    ('Email');

INSERT INTO contact_info (type_id, resume_id, CONTENT)
VALUES
    ((SELECT id FROM contact_types WHERE type = 'Phone'),
     (SELECT id FROM resumes WHERE name = 'John Doe Resume'), '123-456-7890'),

    ((SELECT id FROM contact_types WHERE type = 'Email'),
     (SELECT id FROM resumes WHERE name = 'Jane Smith Resume'), 'jane.smith@example.com');

-- new data:

INSERT INTO categories (name, parent_id)
VALUES
    ('Database Management', (SELECT id FROM categories WHERE name = 'IT')), -- Child of 'IT'
    ('Web Development', (SELECT id FROM categories WHERE name = 'IT')), -- Child of 'IT'
    ('Backend Development', (SELECT id FROM categories WHERE name = 'Web Development')), -- Child of 'Web Development'
    ('Frontend Development', (SELECT id FROM categories WHERE name = 'Web Development')), -- Child of 'Web Development'
    ('Electronics Engineering', (SELECT id FROM categories WHERE name = 'Engineering')); -- Child of 'Engineering'

INSERT INTO users (name, surname, email, password, age, phone_number, avatar, account_type)
VALUES
    ('Alice', 'Johnson', 'alice.johnson@example.com', 'password123', 28, '555-123-4567', 'avatar3.jpg', 'candidate'),
    ('Bob', 'Williams', 'bob.williams@example.com', 'letmein', 32, '555-987-6543', 'avatar4.jpg', 'employer');

INSERT INTO resumes (name, applicant_id, category_id, salary, is_active, created_date, updated_date)
VALUES
    ('Alice Johnson Resume', (SELECT id FROM users WHERE email = 'alice.johnson@example.com'),
     (SELECT id FROM categories WHERE name = 'Frontend Development'), 75000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('Bob Williams Resume', (SELECT id FROM users WHERE email = 'bob.williams@example.com'),
     (SELECT id FROM categories WHERE name = 'Backend Development'), 85000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, updated_time)
VALUES
    ('Database Administrator', 'Seeking a skilled DBA for managing databases',
     (SELECT id FROM categories WHERE name = 'Database Management'), 95000.00, 5, 8, true,
     (SELECT id FROM users WHERE email = 'bob.williams@example.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('UI/UX Designer', 'Looking for a talented UI/UX designer for web projects',
     (SELECT id FROM categories WHERE name = 'Frontend Development'), 90000.00, 3, 5, true,
     (SELECT id FROM users WHERE email = 'bob.williams@example.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES
    ((SELECT id FROM resumes WHERE name = 'Alice Johnson Resume'),
     (SELECT id FROM vacancies WHERE name = 'UI/UX Designer'), true),

    ((SELECT id FROM resumes WHERE name = 'Bob Williams Resume'),
     (SELECT id FROM vacancies WHERE name = 'Database Administrator'), true);

INSERT INTO messages (responded_applicants, content, timestamp)
VALUES
    ((SELECT id FROM responded_applicants
      WHERE resume_id = (SELECT id FROM resumes WHERE name = 'Alice Johnson Resume')),
     'Hello Alice, we are interested in your profile', CURRENT_TIMESTAMP),

    ((SELECT id FROM responded_applicants
      WHERE resume_id = (SELECT id FROM resumes WHERE name = 'Bob Williams Resume')),
     'Thank you for applying, Bob. We will review your resume.', CURRENT_TIMESTAMP);
