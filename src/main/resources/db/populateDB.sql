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
DELETE FROM employees;
DELETE FROM positions;
DELETE FROM departments;

ALTER SEQUENCE PERS_SEQ RESTART WITH 10000;
ALTER SEQUENCE SCHEDULES_SEQ RESTART WITH 10000;
ALTER SEQUENCE absence_reasons_id_seq RESTART WITH 1;
ALTER SEQUENCE absences_id_seq RESTART WITH 1;
ALTER SEQUENCE actions_id_seq RESTART WITH 1;
ALTER SEQUENCE control_points_id_seq RESTART WITH 1;
ALTER SEQUENCE days_off_id_seq RESTART WITH 1;
ALTER SEQUENCE departments_id_seq RESTART WITH 1;
ALTER SEQUENCE edits_id_seq RESTART WITH 1;
ALTER SEQUENCE point_actions_id_seq RESTART WITH 1;
ALTER SEQUENCE point_permits_id_seq RESTART WITH 1;
ALTER SEQUENCE positions_id_seq RESTART WITH 1;
ALTER SEQUENCE weekends_id_seq RESTART WITH 1;

INSERT INTO departments (name, description) VALUES
  ('ООО "Рога и копыта"', 'Компания по произвдству субпродуктов.');

INSERT INTO positions (position, description) VALUES
  ('Директор', 'Глава департамента.');

/*INSERT INTO dep_schedules (dep_id, start_work, end_work, start_lunch, end_lunch) VALUES
  (1, '08:00:00', '17:00:00', '12:00:00', '13:00:00');*/

INSERT INTO absence_reasons (reason, description) VALUES
  ('больничный', 'отсутствие по болезни.');

INSERT INTO employees (dep_id, pos_id, card_num, last_name, first_name, second_name, email) VALUES
  (1, 1, NULL, 333222, 'Иванов', 'Иван', 'Иванович', 'ivanov@mail.ru');

INSERT INTO employee_roles (emp_id, role) VALUES
  (10000, 'ROLE_USER');

INSERT INTO absences (emp_id, reason_id, start_absence, end_absence, description) VALUES
  (10000, 1, '2018-05-03', '2018-05-12', 'больничный лист №12345.');

INSERT INTO absences (emp_id, reason_id, start_absence, end_absence, description) VALUES
  (10000, 1, '2018-05-13', '2018-05-31', 'больничный лист №12345.');;