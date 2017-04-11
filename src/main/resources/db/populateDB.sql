DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, description, local_date_time, calories) VALUES
  (100000,'ужин', '2017-04-09 20:23:58.134292', 300);
INSERT INTO meals (user_id, description, local_date_time, calories) VALUES
  (100000,'LANCh', '2017-04-09 14:23:58.134292', 1000);
INSERT INTO meals (user_id, description, local_date_time, calories) VALUES
  (100001,'ужин', '2017-04-09 20:23:58.134292', 300);
INSERT INTO meals (user_id, description, local_date_time, calories) VALUES
  (100001,'борщ', '2017-04-09 10:23:58.134292', 301);
INSERT INTO meals (user_id, description, local_date_time, calories) VALUES
  (100000,'О.в.С.Я.Н.к.а', '2017-04-09 19:23:00.134292', 500);
INSERT INTO meals (user_id, description, local_date_time, calories) VALUES
  (100000,'суп', '2017-04-09 00:23:58.134292', 100);
INSERT INTO meals (user_id, description, local_date_time, calories) VALUES
  (100000,'завтрак', '2017-04-09 08:23:58.134292', 300);
