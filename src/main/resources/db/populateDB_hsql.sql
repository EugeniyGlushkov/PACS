DELETE FROM visitors;
DELETE FROM employee_roles;
DELETE FROM actions;
DELETE FROM point_permits;
DELETE FROM point_actions;
DELETE FROM dep_schedules;
DELETE FROM emp_schedules;
DELETE FROM edits;
DELETE FROM absences;
DELETE FROM days_off;
DELETE FROM weekends;
DELETE FROM absence_reasons;
DELETE FROM control_points;
DELETE FROM department_chiefs;
DELETE FROM chiefs;
DELETE FROM employees;
DELETE FROM positions;
DELETE FROM departments;

ALTER SEQUENCE PERS_SEQ RESTART WITH 10001;
ALTER SEQUENCE SCHEDULES_SEQ RESTART WITH 10001;
ALTER SEQUENCE ACTIONS_SEQ RESTART WITH 1;
ALTER SEQUENCE POINT_PERMITS_SEQ RESTART WITH 1;
ALTER SEQUENCE POINT_ACTIONS_SEQ RESTART WITH 1;
ALTER SEQUENCE EDITS_SEQ RESTART WITH 1;
ALTER SEQUENCE ABSENCES_SEQ RESTART WITH 1;
ALTER SEQUENCE DAYS_OFF_SEQ RESTART WITH 1;
ALTER SEQUENCE WEEKENDS_SEQ RESTART WITH 1;
ALTER SEQUENCE ABSENCE_REASONS_SEQ RESTART WITH 1;
ALTER SEQUENCE CONTROL_POINTS_SEQ RESTART WITH 1;
ALTER SEQUENCE POSITIONS_SEQ RESTART WITH 1;
ALTER SEQUENCE DEPARTMENTS_SEQ RESTART WITH 1;
ALTER SEQUENCE CHIEFS_SEQ RESTART WITH 1;
ALTER SEQUENCE DEPARTMENT_CHIEFS_SEQ RESTART WITH 1;

INSERT INTO departments (name, description) VALUES
  ('ООО "Рога и копыта"', 'Компания по производству субпродуктов.'), /*id 1*/
  ('Отдел кадров', 'Управление персоналом.'),                        /*id 2*/
  ('Бухгалтерия', 'Финансовые операции и отчетность');               /*id 3*/

INSERT INTO positions (position, description) VALUES
  ('Директор', 'Глава предприятия.'),                       /*id 1*/
  ('Начальник отдела кадров', 'Руководит отделом кадров.'), /*id 2*/
  ('Главный бухгалтер', 'Руководит бухгалтерией.'),         /*id 3*/
  ('Секретарь', 'Делопроизводство.'),                       /*id 4*/
  ('Бухгалтер', 'Сотрудник бухгалтерии.'),                  /*id 5*/
  ('Администратор', 'Администрация базы данных.');          /*id 6*/

INSERT INTO dep_schedules (dep_id, start_work, end_work, start_lunch, end_lunch) VALUES
  (1, '09:00:00', '18:00:00', '12:00:00', '13:00:00'),  /*10001*/
  (2, '08:00:00', '17:00:00', '11:00:00', '12:00:00');  /*10002*/
  /*(3, '09:00:00', '18:00:00', '12:30:00', '13:30:00');*/  /*10003*/

INSERT INTO weekends (dep_id, weekday_id) VALUES
  (1, 6), /*id 1*/
  (1, 7), /*id 2*/
  (2, 6), /*id 3*/
  (2, 7), /*id 4*/
  (3, 5), /*id 5*/
  (3, 6), /*id 6*/
  (3, 7); /*id 7*/

INSERT INTO days_off (dep_id, date) VALUES
  (1, '2018-01-01'), /*id 1*/
  (2, '2018-01-01'), /*id 2*/
  (3, '2018-01-01'), /*id 3*/
  (1, '2018-01-02'); /*id 4*/

INSERT INTO control_points (serial_id, description) VALUES
  ('SA1232', 'Турникет №1 на главной проходной.'),             /*id 1*/
  ('SQ145', 'Турникет №2 на главной проходной.'),              /*id 2*/
  ('45SDD', 'Электронный замок, дверь в подсобное помещение.'), /*id 3*/
  ('C34', 'Электронный замок, лифт №1, первый этаж.');         /*id 4*/

INSERT INTO absence_reasons (reason, description) VALUES
  ('больничный', 'отсутствие по болезни.'),       /*id 1*/
  ('командировка', 'нахождение в командировке.'), /*id 2*/
  ('отпуск', 'отдых в отпуске.'),                 /*id 3*/
  ('отгул', 'отсутствие по причине отгула.');     /*id 4*/

INSERT INTO visitors (temp_num, last_name, first_name, second_name, description, enter_time, exit_time) VALUES
  ('300320181', 'Иванов', 'Иван', 'Иванович', 'Посещение отдела кадров.', '2018-03-30 08:45:00', '2018-03-30 13:52:00'),            /*10001*/
  ('100120192', 'Петров', 'Пётр', 'Петрович', 'Посещение отдела инвентаризации.', '2019-01-10 10:22:00', '2019-01-10 13:54:00'),    /*10002*/
  ('110120191', 'Сидоров', 'Сидор', 'Сидорович', 'Посещение отдела инвентаризации.', '2019-01-10 12:35:00', '2019-01-10 12:44:00'), /*10003*/
  ('110120192', 'Андреев', 'Тимур', 'Иванович', 'Встреча с секретарём.', '2019-01-10 12:40:00', NULL),                              /*10004*/
  ('110120193', 'Романов', 'Пётр', 'Григорьевич', 'Посещение директора.', NULL, NULL);                                              /*10005*/

INSERT INTO point_actions (controlpoint_id, acttype_id) VALUES
  (1, 1), /*id 1*/
  (1, 2), /*id 2*/
  (2, 1), /*id 3*/
  (2, 2), /*id 4*/
  (3, 4), /*id 5*/
  (4, 4); /*id 6*/

INSERT INTO employees (dep_id, pos_id, card_num, last_name, first_name, second_name, email) VALUES
  (1, 1, 333222, 'Иванов', 'Иван', 'Иванович', 'ivanov@mail.ru'),     /*10006*/
  (2, 2, 225485, 'Тищенко', 'Лидия', 'Петровна', 'tlpet@gmail.com'),  /*10007*/
  (3, 3, 256432, 'Иванков', 'Пётр', 'Ильич', 'ivpil@list.ru'),        /*10008*/
  (2, 4, 965677, 'Сидоркина', 'Анна', 'Андреевна', 'secret@list.ru'), /*10009*/
  (3, 5, 124344, 'Батурина', 'Ольга', 'Игоревна', 'buhg@list.ru'),    /*10010*/
  (2, 6, 555433, 'Балабанов', 'Евгений', 'Олегович', 'admin@bk.ru');  /*10011*/

INSERT INTO employee_roles (emp_id, role) VALUES
  (10006, 'ROLE_USER'),
  (10006, 'ALL_READ'),
  (10007, 'ROLE_DEPSREAD'),
  (10007, 'ROLE_EMPSWRIGHT'),
  (10007, 'ROLE_USER'),
  (10008, 'ROLE_DEPSREAD'),
  (10008, 'ROLE_USER'),
  (10009, 'ROLE_USER'),
  (10010, 'ROLE_USER'),
  (10011, 'ROLE_USER'),
  (10011, 'ROLE_ADMIN');

INSERT INTO emp_schedules (emp_id, start_work, end_work, start_lunch, end_lunch) VALUES
  (10006, NULL, NULL, NULL, NULL),                          /*10003*/
  (10009, '09:00:00', '18:00:00', '12:00:00', '13:00:00');  /*10004*/

  INSERT INTO department_chiefs (dep_id, emp_id) VALUES
  (1, 10006), /*1*/
  (2, 10007), /*2*/
  (3, 10008); /*3*/

INSERT INTO absences (emp_id, reason_id, start_absence, end_absence, description) VALUES
  (10006, 1, '2018-05-03', '2018-05-12', 'больничный лист №12345.');

INSERT INTO absences (emp_id, reason_id, start_absence, end_absence, description) VALUES
  (10006, 1, '2018-05-13', '2018-05-31', 'больничный лист №12345.');;