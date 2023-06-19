DELETE FROM meals;
DELETE FROM user_role;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, description, calories, date_time)
VALUES (100000, 'Завтрак', 1500, '2010-2-1 10:00'),
       (100000, 'Обед', 2100, '2010-2-1 12:00'),
       (100001, 'Перекус', 400, '2010-2-1 12:00'),
       (100000, 'Ужин', 1100, '2010-2-1 18:00');