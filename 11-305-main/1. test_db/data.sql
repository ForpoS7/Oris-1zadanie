-- Скрипт для заполнения таблицы users

CREATE TABLE IF NOT EXISTS users (
    firstname VARCHAR(255),
    secondname VARCHAR(255),
    id SERIAL PRIMARY KEY,
    age INT,
    date_of_birth DATE,
    coolness INT CHECK (coolness >= 0 AND coolness <= 100),
    arm_span NUMERIC(3, 2)
);

INSERT INTO users (firstname, secondname, age, date_of_birth, coolness, arm_span) VALUES
    ('Иван', 'Иванов', 30, '1993-07-15', 90, 1.80),
    ('Елена', 'Петрова', 25, '1998-03-20', 95, 1.75),
    ('Алексей', 'Сидоров', 42, '1981-11-05', 75, 2.20),
    ('Ольга', 'Смирнова', 28, '1995-06-22', 80, 1.65),
    ('Дмитрий', 'Кузнецов', 35, '1988-09-10', 65, 1.85);