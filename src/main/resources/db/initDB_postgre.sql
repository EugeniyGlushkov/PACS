DROP TABLE IF EXISTS visitors;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS actions;
DROP TABLE IF EXISTS dep_schedules;
DROP TABLE IF EXISTS emp_schedules;
DROP TABLE IF EXISTS edits;
DROP TABLE IF EXISTS absences;
DROP TABLE IF EXISTS days_off;
DROP TABLE IF EXISTS dep_weekends;
DROP TABLE IF EXISTS absence_reasons;
DROP TABLE IF EXISTS week_days;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS departments;

DROP SEQUENCE IF EXISTS PERS_SEQ;
DROP SEQUENCE IF EXISTS SCHEDULES_SEQ;

CREATE SEQUENCE PERS_SEQ START 10000;
CREATE SEQUENCE SCHEDULES_SEQ START 10000;

CREATE TABLE departments
(
  id    SERIAL,
  name  VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX departments_unique_name_idx ON departments (name);

CREATE TABLE positions
(
  id          SERIAL PRIMARY KEY,
  position    VARCHAR(100) UNIQUE NOT NULL,
  description VARCHAR NOT NULL
);

CREATE TABLE employees
(
  id          INTEGER PRIMARY KEY  DEFAULT  nextval('PERS_SEQ'),
  dep_id      INTEGER NOT NULL,
  pos_id      INTEGER NOT NULL,
  card_num    INTEGER NOT NULL,
  first_name  VARCHAR(255) NOT NULL,
  second_name VARCHAR(255) NOT NULL,
  last_name   VARCHAR(255) NOT NULL,
  email       VARCHAR,
  FOREIGN KEY (dep_id) REFERENCES departments (id),
  FOREIGN KEY (pos_id) REFERENCES positions (id)
);
CREATE UNIQUE INDEX employees_unique_card_num_idx ON employees (card_num);
CREATE UNIQUE INDEX employees_unique_emale_idx ON employees (email);

CREATE TABLE week_days
(
  id INTEGER PRIMARY KEY,
  name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE absence_reasons
(
  id SERIAL PRIMARY KEY,
  reason VARCHAR(255) UNIQUE NOT NULL,
  description VARCHAR NOT NULL
);

CREATE TABLE dep_weekends
(
  id INTEGER PRIMARY KEY DEFAULT nextval('SCHEDULES_SEQ'),
  dep_id INTEGER NOT NULL,
  weekday_id INTEGER NOT NULL,
  FOREIGN KEY (dep_id) REFERENCES departments (id),
  FOREIGN KEY (weekday_id) REFERENCES week_days (id),
  CONSTRAINT depid_weekdayid_idx UNIQUE (dep_id, weekday_id)
);
CREATE INDEX depweekends_depid_idx ON dep_weekends (dep_id);

