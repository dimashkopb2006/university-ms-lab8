CREATE TABLE IF NOT EXISTS students (
    id               BIGSERIAL PRIMARY KEY,
    first_name       VARCHAR(100) NOT NULL,
    last_name        VARCHAR(100) NOT NULL,
    email            VARCHAR(150) NOT NULL UNIQUE,
    major            VARCHAR(100),
    enrollment_year  INTEGER      NOT NULL
);

INSERT INTO students (first_name, last_name, email, major, enrollment_year) VALUES
 ('Dinmukhammed', 'Kopbossyn', 'd.kopbossyn@iitu.edu.kz', 'Computer Science', 2024),
 ('Aruzhan',      'Sadykova',  'a.sadykova@iitu.edu.kz',  'Information Systems', 2023)
ON CONFLICT (email) DO NOTHING;
