DELETE FROM visitors;
DELETE FROM employee_roles;
DELETE FROM actions;
DELETE FROM dep_schedules;
DELETE FROM emp_schedules;
DELETE FROM edits;
DELETE FROM absences;
DELETE FROM days_off;
DELETE FROM weekends;
DELETE FROM absence_reasons;
DELETE FROM week_days;
DELETE FROM action_types;
DELETE FROM employees;
DELETE FROM positions;
DELETE FROM departments;

ALTER SEQUENCE PERS_SEQ RESTART WITH 10000;
ALTER SEQUENCE SCHEDULES_SEQ RESTART WITH 10000;

INSERT INTO departments (name) VALUES
  ('ООО "Рога и копыта"', 'Компания по произвдству субпродуктов.');

INSERT INTO positions (position, description) VALUES
  ('Директор', 'Глава департамента.');

INSERT INTO employees (dep_id, pos_id, card_num, last_name, first_name, second_name, email) VALUES
  (1, 1, 333222, 'Иванов', 'Иван', 'Иванович', 'ivanov@mail.ru');

INSERT INTO absence_reasons (reason, description) VALUES
  ('больничный', 'отсутствие по болезни.');

INSERT INTO absences (emp_id, reason_id, start_absence, end_absence, description) VALUES
  (10000, 1, '2018-05-03', '2018-05-12', 'больничный лист №12345.');

INSERT INTO absences (emp_id, reason_id, start_absence, end_absence, description) VALUES
  (10000, 1, '2018-05-13', '2018-05-31', 'больничный лист №12345.');