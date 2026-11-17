CREATE TABLE IF NOT EXISTS courses (
    id           BIGSERIAL PRIMARY KEY,
    course_name  VARCHAR(150) NOT NULL,
    course_code  VARCHAR(20)  NOT NULL UNIQUE,
    credits      INTEGER      NOT NULL,
    instructor   VARCHAR(150)
);

INSERT INTO courses (course_name, course_code, credits, instructor) VALUES
 ('Development of Web Applications in Java Spring', 'SFT6566', 5, 'Instructor A'),
 ('Computer Graphics',                              'CSE2205', 5, 'Instructor B'),
 ('Databases',                                      'CSE2101', 6, 'Instructor C')
ON CONFLICT (course_code) DO NOTHING;
