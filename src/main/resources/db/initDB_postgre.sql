DROP TABLE IF EXISTS visitors;
DROP TABLE IF EXISTS employee_roles;
DROP TABLE IF EXISTS actions;
DROP TABLE IF EXISTS dep_schedules;
DROP TABLE IF EXISTS emp_schedules;
DROP TABLE IF EXISTS edits;
DROP TABLE IF EXISTS absences;
DROP TABLE IF EXISTS days_off;
DROP TABLE IF EXISTS weekends;
DROP TABLE IF EXISTS absence_reasons;
DROP TABLE IF EXISTS week_days;
DROP TABLE IF EXISTS action_types;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS departments;

DROP SEQUENCE IF EXISTS PERS_SEQ;
DROP SEQUENCE IF EXISTS SCHEDULES_SEQ;

CREATE SEQUENCE PERS_SEQ START 10000;
CREATE SEQUENCE SCHEDULES_SEQ START 10000;

CREATE TABLE departments
(
  id    SERIAL PRIMARY KEY,
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

CREATE TABLE action_types
(
  id SERIAL PRIMARY KEY,
  action VARCHAR(50) UNIQUE NOT NULL,
  description VARCHAR
);

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

CREATE TABLE weekends
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('SCHEDULES_SEQ'),
  dep_id     INTEGER NOT NULL,
  weekday_id INTEGER NOT NULL,
  FOREIGN KEY (dep_id) REFERENCES departments (id) ON DELETE CASCADE,
  FOREIGN KEY (weekday_id) REFERENCES week_days (id),
  CONSTRAINT depid_weekdayid_idx UNIQUE (dep_id, weekday_id)
);
CREATE INDEX weekends_depid_idx ON weekends (dep_id);

CREATE TABLE days_off
(
  id INTEGER PRIMARY KEY DEFAULT nextval('SCHEDULES_SEQ'),
  dep_id INTEGER NOT NULL,
  date DATE NOT NULL,
  FOREIGN KEY (dep_id) REFERENCES departments (id) ON DELETE CASCADE,
  CONSTRAINT depid_daysoff_idx UNIQUE (dep_id, date)
);
CREATE INDEX daysoff_depid_idx ON days_off (dep_id);

CREATE TABLE absences
(
  id SERIAL PRIMARY KEY,
  emp_id INTEGER NOT NULL,
  reason_id INTEGER NOT NULL,
  start_absence DATE NOT NULL,
  end_absence DATE NOT NULL,
  description VARCHAR NOT NULL,
  FOREIGN KEY (emp_id) REFERENCES employees (id),
  FOREIGN KEY (reason_id) REFERENCES absence_reasons (id),
  CONSTRAINT abs_start_end_con CHECK (start_absence < end_absence)
);
CREATE INDEX abs_empid_idx ON absences (emp_id);

CREATE TABLE edits
(
  id SERIAL PRIMARY KEY,
  emp_id INTEGER NOT NULL,
  edit_date TIMESTAMP DEFAULT now() NOT NULL,
  edit_type VARCHAR(100) NOT NULL,
  description VARCHAR NOT NULL,
  FOREIGN KEY (emp_id) REFERENCES employees (id)
);

CREATE TABLE emp_schedules
(
  id INTEGER PRIMARY KEY DEFAULT nextval('SCHEDULES_SEQ'),
  emp_id INTEGER NOT NULL,
  start_work TIME,
  end_work TIME,
  start_lunch TIME,
  end_lunch TIME,
  FOREIGN KEY (emp_id) REFERENCES employees (id),
  CONSTRAINT empsched_start_end_con CHECK (start_work < start_lunch
                                           AND start_lunch < end_lunch
                                           AND end_lunch < end_work)
);
CREATE UNIQUE INDEX empsched_unique_empid_idx ON emp_schedules (emp_id);

CREATE TABLE dep_schedules
(
  id INTEGER PRIMARY KEY DEFAULT nextval('SCHEDULES_SEQ'),
  dep_id INTEGER NOT NULL,
  start_work TIME,
  end_work TIME,
  start_lunch TIME,
  end_lunch TIME,
  FOREIGN KEY (dep_id) REFERENCES departments (id) ON DELETE CASCADE,
  CONSTRAINT depsched_start_end_con CHECK (start_work < start_lunch
                                           AND start_lunch < end_lunch
                                           AND end_lunch < end_work)
);
CREATE UNIQUE INDEX depsched_unique_depid_idx ON dep_schedules (dep_id);

CREATE TABLE actions
(
  id SERIAL PRIMARY KEY,
  emp_id INTEGER NOT NULL,
  acttype_id INTEGER NOT NULL,
  time TIMESTAMP DEFAULT now() NOT NULL,
  FOREIGN KEY (emp_id) REFERENCES employees (id),
  FOREIGN KEY (acttype_id) REFERENCES action_types (id),
  CONSTRAINT act_emp_time_con UNIQUE (emp_id, time)
);
CREATE INDEX act_empid_idx ON actions (emp_id);
CREATE INDEX act_time_idx ON actions (time);

CREATE TABLE employee_roles
(
  emp_id INTEGER NOT NULL,
  role VARCHAR(255) NOT NULL,
  FOREIGN KEY (emp_id) REFERENCES employees (id),
  CONSTRAINT employee_roles_con UNIQUE (emp_id, role)
);

CREATE TABLE visitors
(
  id INTEGER PRIMARY KEY DEFAULT nextval('PERS_SEQ'),
  temp_num VARCHAR(255) UNIQUE NOT NULL,
  last_name   VARCHAR(255) NOT NULL,
  first_name  VARCHAR(255) NOT NULL,
  second_name VARCHAR(255) NOT NULL,
  description VARCHAR NOT NULL,
  enter_time TIMESTAMP,
  exit_time TIMESTAMP
);
CREATE INDEX visitors_names_idx ON visitors (last_name, first_name, second_name);
