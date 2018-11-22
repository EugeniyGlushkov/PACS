DROP TRIGGER IF EXISTS chek_abs ON absences;
DROP TRIGGER IF EXISTS chek_act ON actions;
DROP TRIGGER IF EXISTS new_emp ON employees;

DROP FUNCTION IF EXISTS new_absence();
DROP FUNCTION IF EXISTS new_action();
DROP FUNCTION IF EXISTS new_employee();

DROP TABLE IF EXISTS visitors;
DROP TABLE IF EXISTS employee_roles;
DROP TABLE IF EXISTS actions;
DROP TABLE IF EXISTS point_permits;
DROP TABLE IF EXISTS point_actions;
DROP TABLE IF EXISTS dep_schedules;
DROP TABLE IF EXISTS emp_schedules;
DROP TABLE IF EXISTS edits;
DROP TABLE IF EXISTS absences;
DROP TABLE IF EXISTS days_off;
DROP TABLE IF EXISTS weekends;
DROP TABLE IF EXISTS absence_reasons;
DROP TABLE IF EXISTS edit_types;
DROP TABLE IF EXISTS week_days;
DROP TABLE IF EXISTS action_types;
DROP TABLE IF EXISTS control_points;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS departments;

DROP SEQUENCE IF EXISTS PERS_SEQ;
DROP SEQUENCE IF EXISTS SCHEDULES_SEQ;

/*
Последовательнсть для первичных ключей отношений
-"Сотрудники"
-"Посетители"
 */
CREATE SEQUENCE PERS_SEQ START 10000;
/*
Последовательность для первичных ключей отношений
-"Расписание департаментов"
-"Расписание сотрудников"
 */
CREATE SEQUENCE SCHEDULES_SEQ START 10000;

/*
Отношение "Департаменты"
содержит соответственно:
-первичный ключ;
-название департамента, должно быть уникальным.
-описание департамента.
*/
CREATE TABLE departments
(
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  description VARCHAR      NOT NULL
);
CREATE UNIQUE INDEX departments_unique_name_idx
  ON departments (name);

/*
Отношение "Должности"
содержит соответственно:
-первичный ключ;
-название должности: должно быть уникальным;
-комментарий: описание должности.
 */
CREATE TABLE positions
(
  id          SERIAL PRIMARY KEY,
  position    VARCHAR(100) UNIQUE NOT NULL,
  description VARCHAR             NOT NULL
);

/*
Отношение "Сотрудники"
содержит соответственно:
-первичный ключ;
-id департамента в котором работатет, если null, то считется уволенным;
-id занимаемой должности;
-серийный номер ключ-карты, должен быть уникальным;
-фамилия;
-имя;
-отчество;
-email, должен быть уникальным.

 */
CREATE TABLE employees
(
  id          INTEGER PRIMARY KEY  DEFAULT nextval('PERS_SEQ'),
  dep_id      INTEGER,
  pos_id      INTEGER      NOT NULL,
  card_num    INTEGER      NOT NULL,
  last_name   VARCHAR(100) NOT NULL,
  first_name  VARCHAR(100) NOT NULL,
  second_name VARCHAR(100) NOT NULL,
  email       VARCHAR(100) NOT NULL,
  FOREIGN KEY (dep_id) REFERENCES departments (id),
  FOREIGN KEY (pos_id) REFERENCES positions (id)
);
CREATE UNIQUE INDEX employees_unique_card_num_idx
  ON employees (card_num);
CREATE UNIQUE INDEX employees_unique_emale_idx
  ON employees (email);

/*
Отношение "Контрольная точка" (турникет, электронный замок и т.п.)
содержит соответственно:
-первичный ключ;
-серийный номер контрольной точки;
-описание контрольной точки.
 */
CREATE TABLE control_points
(
  id          SERIAL PRIMARY KEY,
  serial_id   VARCHAR(50) UNIQUE NOT NULL,
  description VARCHAR            NOT NULL
);

/*
Отношение "Тип действия" (вход, выход и т.п.)
содержит соответственно:
-первичный ключ;
-название действия;
-описание действия.
 */
CREATE TABLE action_types
(
  id          SERIAL PRIMARY KEY,
  action      VARCHAR(50) UNIQUE NOT NULL,
  description VARCHAR            NOT NULL
);

/*
Отношение "Действия на точке", допустимые действия для
каждой конкретной точки.
содержит соответственно:
-первичный ключ;
-id контрольной точки;
-id действия.
Ограничение: каждой точке yt может соответствовать два одинаковых типа действия.
 */
CREATE TABLE point_actions
(
  id              SERIAL PRIMARY KEY,
  controlpoint_id INTEGER NOT NULL,
  acttype_id      INTEGER NOT NULL,
  FOREIGN KEY (controlpoint_id) REFERENCES control_points (id) ON DELETE CASCADE,
  FOREIGN KEY (acttype_id) REFERENCES action_types (id) ON DELETE CASCADE,
  CONSTRAINT conpoint_acttype_idx UNIQUE (controlpoint_id, acttype_id)
);

/*
Отношение-справочник "Дни недели"
содержит соответственно:
-первичный ключ;
-код (навание дня недели), должно быть уникальным.
 */
CREATE TABLE week_days
(
  id   SMALLINT PRIMARY KEY,
  code VARCHAR UNIQUE NOT NULL
);

/*
Отношение "Типы правок", типы которые могут принимать правки в соответствующем отношении
(добавление, удаление, обносление)
содержит соответственно:
-первичный ключ;
-код правки;
 */
CREATE TABLE edit_types
(
  id   SMALLINT PRIMARY KEY,
  code VARCHAR UNIQUE NOT NULL
);

/*
Отношение "Причины отсутствия" (больничный, командировка и т.п.)
содержит соответственно:
-первичный ключ;
-название причины, должно быть уникальным;
-описание причины.
 */
CREATE TABLE absence_reasons
(
  id          SERIAL PRIMARY KEY,
  reason      VARCHAR(255) UNIQUE NOT NULL,
  description VARCHAR             NOT NULL
);

/*
Отношение "Выходные", определяет выходные дни для конкретного департамента
содержит соответственно:
-первичный ключ;
-id департамента;
-id дня недели.
сочетания департамента и дня недели должны быть уникальны.
 */
CREATE TABLE weekends
(
  id         SERIAL PRIMARY KEY,
  dep_id     INTEGER NOT NULL,
  weekday_id INTEGER NOT NULL,
  FOREIGN KEY (dep_id) REFERENCES departments (id) ON DELETE CASCADE,
  FOREIGN KEY (weekday_id) REFERENCES week_days (id),
  CONSTRAINT depid_weekdayid_idx UNIQUE (dep_id, weekday_id)
);
CREATE INDEX weekends_depid_idx
  ON weekends (dep_id);

/*
Отношение "Нерабочие и праздничные дни", определяет непериодические нерабочие дни.
Заполняется отдельно для каждго департамента.
содержит соответственно:
-первичный ключ;
-id департамента;
-дата.
Сочетание даты и отдела должно быть уникальным.
 */
CREATE TABLE days_off
(
  id     SERIAL PRIMARY KEY,
  dep_id INTEGER NOT NULL,
  date   DATE    NOT NULL,
  FOREIGN KEY (dep_id) REFERENCES departments (id) ON DELETE CASCADE,
  CONSTRAINT depid_daysoff_idx UNIQUE (dep_id, date)
);
CREATE INDEX daysoff_depid_idx
  ON days_off (dep_id);

/*
Отношение "Отсутствия", периоды отсутствия на работе по какой-либо причине.
Отсутствие вне существующих периодов считаются прогулом.
содержит соответственно:
-первичный ключ;
-id сотрудника:
-id причины отсутствия;
-дата начала отсутствия;
-дата окончания отсутствия;
-описание;
дата начала должна быть раньше даты окончания.
 */
CREATE TABLE absences
(
  id            SERIAL PRIMARY KEY,
  emp_id        INTEGER NOT NULL,
  reason_id     INTEGER NOT NULL,
  start_absence DATE    NOT NULL,
  end_absence   DATE    NOT NULL,
  description   VARCHAR NOT NULL,
  FOREIGN KEY (emp_id) REFERENCES employees (id),
  FOREIGN KEY (reason_id) REFERENCES absence_reasons (id),
  CONSTRAINT abs_start_end_con CHECK (start_absence < end_absence)
);
CREATE INDEX abs_empid_idx
  ON absences (emp_id);
CREATE UNIQUE INDEX abs_empid_start_idx
  ON absences (emp_id, start_absence);
CREATE UNIQUE INDEX abs_empid_end_idx
  ON absences (emp_id, end_absence);

/*
Отошение "Правки", хранит историю изменений (редактирование, удаление, добавление)
содержит соответственно:
-первичный ключ;
-id типа правки;
-id сотрудника осуществившего изменение;
-дата изменения;
-описание изменения.
Один работник не может иметь несколько правок одновременно.
 */
CREATE TABLE edits
(
  id          SERIAL PRIMARY KEY,
  type_id     INTEGER                 NOT NULL,
  emp_id      INTEGER                 NOT NULL,
  edit_date   TIMESTAMP DEFAULT now() NOT NULL,
  description VARCHAR                 NOT NULL,
  FOREIGN KEY (type_id) REFERENCES edit_types (id),
  FOREIGN KEY (emp_id) REFERENCES employees (id)
);
CREATE UNIQUE INDEX edits_emp_date_idx
  ON edits (emp_id, edit_date);

/*
Отношение "Расписания сотрудников", хранит расписание для конкретного
сотрудника, если для какого либо сотрудника расписание отсутствует, то
оно соответствует общему расписанию для департамента из отношения "Расписания
департаментов"
содержит соответственно:
-первичный ключ;
-id сотрудника;
-время начала работы;
-время окончания работы;
-время начала обеда;
-время окончания обеда.
ограничение: начало работы раньше начала обеда, начало обеда раньше конца обеда,
конец обеда раньше конца рабочего дня.
 */
CREATE TABLE emp_schedules
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('SCHEDULES_SEQ'),
  emp_id      INTEGER NOT NULL,
  start_work  TIME,
  end_work    TIME,
  start_lunch TIME,
  end_lunch   TIME,
  FOREIGN KEY (emp_id) REFERENCES employees (id) ON DELETE CASCADE,
  CONSTRAINT empsched_start_end_con CHECK (start_work < start_lunch
                                           AND start_lunch < end_lunch
                                           AND end_lunch < end_work)
);
CREATE UNIQUE INDEX empsched_unique_empid_idx
  ON emp_schedules (emp_id);

/*
Отношение "Расписание департамента", действует для всех сотрудников у когторых
отсутствует индивидуальное раписание.
содержит соответственно:
-первичный ключ;
-id департамента;
-время начала работы;
-время окончания работы;
-время начала обеда;
-время окончания обеда.
ограничение: начало работы раньше начала обеда, начало обеда раньше конца обеда,
конец обеда раньше конца рабочего дня.
 */
CREATE TABLE dep_schedules
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('SCHEDULES_SEQ'),
  dep_id      INTEGER NOT NULL,
  start_work  TIME,
  end_work    TIME,
  start_lunch TIME,
  end_lunch   TIME,
  FOREIGN KEY (dep_id) REFERENCES departments (id) ON DELETE CASCADE,
  CONSTRAINT depsched_start_end_con CHECK (start_work < start_lunch
                                           AND start_lunch < end_lunch
                                           AND end_lunch < end_work)
);
CREATE UNIQUE INDEX depsched_unique_depid_idx
  ON dep_schedules (dep_id);

/*
Отношение "Разрешения для контрольных точек", какие действия разрешены
данному сотруднику на данной точке
содержит соответственно:
-первичный ключ;
-id действия, допустимого на данной точке;
-id сотрудника;
Ограничение: одному сотруднику не может соответствовать несколько одинаковых контрольных точек.
 */
CREATE TABLE point_permits
(
  id          SERIAL PRIMARY KEY,
  pointact_id INTEGER NOT NULL,
  emp_id      INTEGER NOT NULL,
  FOREIGN KEY (pointact_id) REFERENCES point_actions (id),
  FOREIGN KEY (emp_id) REFERENCES employees (id),
  CONSTRAINT permits_copoint_emp_con UNIQUE (pointact_id, emp_id)
);

/*
Отношение "События", хранит действия совершенные сотрудниками
содержит соответственно:
-первичный ключ;
-id сотрудника, совершившего действие;
-id действия на данной точке;
-время, когда было произведено действие.
Ограничение: одному времени не может соответствовать несколько одинаковых действий для
одного сотрудника.
 */
CREATE TABLE actions
(
  id          SERIAL PRIMARY KEY,
  emp_id      INTEGER                 NOT NULL,
  pointact_id INTEGER                 NOT NULL,
  time        TIMESTAMP DEFAULT now() NOT NULL,
  FOREIGN KEY (emp_id) REFERENCES employees (id),
  FOREIGN KEY (pointact_id) REFERENCES point_actions (id),
  CONSTRAINT act_emp_time_con UNIQUE (emp_id, time)
);
CREATE INDEX act_pointpermit_id_idx
  ON actions (emp_id);
CREATE INDEX act_time_idx
  ON actions (time);

/*
Отношение "Роли сотрудников", хранит роли определяющие права доступа к системе
содержит соответственно:
-id сотрудника которому соответствует роль (одному сотруднику может
соответствовать несколько ролей)
-наименование роли (обычный пользователь, одминистратор и т.п.)
Ограничение: одному сотруднику не может соответствовать две одинаковые роли.
 */
CREATE TABLE employee_roles
(
  emp_id INTEGER      NOT NULL,
  role   VARCHAR(255) NOT NULL,
  FOREIGN KEY (emp_id) REFERENCES employees (id),
  CONSTRAINT employee_roles_con UNIQUE (emp_id, role)
);

/*
Отношение "Посетители", хранит информацию о посетителях, которые
не имеют пропусков
содержит соответственно:
-первичный ключ;
-временный номер, используется для нумерации временный пропусков;
-фамилия;
-имя;
-отчество;
-описание, содержит дополнительную информацию: цель визита и т.п.
-время входа, если отсутствует - посетитель ещё не вошёл;
-время выхода, если отсутствует - посетитель ещё не вышел;
 */
CREATE TABLE visitors
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('PERS_SEQ'),
  temp_num    VARCHAR(20) UNIQUE NOT NULL,
  last_name   VARCHAR(100)       NOT NULL,
  first_name  VARCHAR(100)       NOT NULL,
  second_name VARCHAR(100)       NOT NULL,
  description VARCHAR            NOT NULL,
  enter_time  TIMESTAMP,
  exit_time   TIMESTAMP,
  CONSTRAINT visitors_times_con CHECK (NOT (enter_time IS NULL AND exit_time IS NOT NULL))
);
CREATE INDEX visitors_names_idx
  ON visitors (temp_num, last_name, first_name, second_name);

/*
Функция добавляет расписание работника со значениями из расписания его отдела.
 */
CREATE FUNCTION new_employee()
  RETURNS TRIGGER AS
$new_emp$
BEGIN
  INSERT INTO emp_schedules (emp_id, start_work, end_work, start_lunch, end_lunch) VALUES
    (NEW.id,
     (SELECT start_work
      FROM dep_schedules
      WHERE dep_schedules.id = NEW.dep_id),
     (SELECT end_work
      FROM dep_schedules
      WHERE dep_schedules.id = NEW.dep_id),
     (SELECT start_lunch
      FROM dep_schedules
      WHERE dep_schedules.id = NEW.dep_id),
     (SELECT end_lunch
      FROM dep_schedules
      WHERE dep_schedules.id = NEW.dep_id));
END;
$new_emp$ LANGUAGE plpgsql;

/*
Функция для проверки того, что добавляемый интервал времени отсутствия не пересекается
интервалами уже существующих отсутствий: работник не может одновременно быть в отпуске и командировке.
При выявлении пересечения выбрасывается исключение.
 */
CREATE FUNCTION new_absence()
  RETURNS TRIGGER AS
$chek_abs$
BEGIN
  IF EXISTS(
      SELECT *
      FROM absences a
      WHERE a.emp_id = NEW.emp_id
            AND
            ((NEW.start_absence >= a.start_absence AND NEW.start_absence <= a.end_absence)
             OR (NEW.end_absence >= a.start_absence AND NEW.end_absence <= a.end_absence)
            ))
  THEN
    RAISE EXCEPTION 'Impossible insert, because new data intesepts with old data!';
  END IF;
  RETURN NEW;
END;
$chek_abs$ LANGUAGE plpgsql;

/*
Функция для проверки того, что выбранный сотрудник имеет право производить
действие указанное в добавляемом событии. Проверяется наличие записи
добавляемого действия в списке разрешенных событий для данного работника.
 */
CREATE FUNCTION new_action()
  RETURNS TRIGGER AS
$chek_act$
BEGIN
  IF NOT EXISTS(
      SELECT *
      FROM point_permits pp
      WHERE pp.emp_id = NEW.emp_id
            AND pp.pointact_id = NEW.pointact_id
  )
  THEN
    RAISE EXCEPTION 'Impossible insert, because eployee with id=% has not point action with id=% !',
    NEW.emp_id, NEW.pointact_id;
  END IF;
  RETURN NEW;
END;
$chek_act$ LANGUAGE plpgsql;

/*
Триггер срабатывает после добавления нового работника и вызывает функцию new_employee().
 */
CREATE TRIGGER new_emp
AFTER INSERT
  ON employees
FOR EACH ROW
EXECUTE PROCEDURE new_employee();

/*
Триггер, срабатывающий перед добавлением новых данных в отношение "Отсутствия"
и вызывающий функцию new_absence().
 */
CREATE TRIGGER chek_abs
BEFORE INSERT
  ON absences
FOR EACH ROW
EXECUTE PROCEDURE new_absence();

/*
Триггер, срабатывающий перед добавлением новых данных в отношение "События"
и вызывающий функцию new_action().
 */
CREATE TRIGGER chek_act
BEFORE INSERT
  ON actions
FOR EACH ROW
EXECUTE PROCEDURE new_action();

INSERT INTO week_days (id, code) VALUES
  (1, 'MONDAY'),
  (2, 'TUESDAY'),
  (3, 'WEDNESDAY'),
  (4, 'THURSDAY'),
  (5, 'FRIDAY'),
  (6, 'SATURDAY'),
  (7, 'SUNDAY');

INSERT INTO edit_types (id, code) VALUES
  (1, 'CREATE'),
  (2, 'UPDATE'),
  (3, 'DELETE');;



