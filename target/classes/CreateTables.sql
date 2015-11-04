--CREATE DATABASE site CHARACTER SET utf8;

CREATE  TABLE site.users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

CREATE TABLE site.user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

CREATE TABLE site.announcements (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  publication_date timestamp NOT NULL,
  header varchar(25) NOT NULL,
  title varchar(30) NOT NULL,
  content varchar(400) NOT NULL,
  PRIMARY KEY (id));--,
  --KEY fk_username_idx (username),
  --CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

INSERT INTO users(username,password,enabled)
VALUES ('mkyong','123456', true);
INSERT INTO users(username,password,enabled)
VALUES ('alex','123456', true);

INSERT INTO user_roles (username, role)
VALUES ('mkyong', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('mkyong', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('alex', 'ROLE_USER');

--INSERT INTO announcements (username, publication_date, header, title, content)
--    VALUES ('Паша', now(), 'продажа', 'Ауди совсем новая', 'Продам совсем новую Ауди 2015 года выпуска');
--INSERT INTO announcements (username, publication_date, header, title, content)
--    VALUES ('Маша', now(), 'покупка', 'Куплю куртку новую', 'Куплю куртку новую для себя размер 48');
--INSERT INTO announcements (username, publication_date, header, title, content)
--    VALUES ('Глаша', now(), 'аренда', 'Аренда дома для семьи', 'Сниму дом за городом для семьи из 8 человек');
--INSERT INTO announcements (username, publication_date, header, title, content)
--    VALUES ('mkyong', now(), 'услуги', 'Помогу с трудоустройством в ИТ', 'Готовлю для собеседования в фирму "ИТ технологии"');
--INSERT INTO announcements (username, publication_date, header, title, content)
--    VALUES ('Глаша', now(), 'знакомства', 'Ищу огородников-проффесионалов', 'Занимаюсь разведение сельдерея. Ищу огородников, занимающихся тем же.');   