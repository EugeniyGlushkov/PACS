package ru.alvisid.pacs.model.enumActivate;

import ru.alvisid.pacs.model.WeekDay;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A week day from dictionary the week_days dictionary in the data base.
 */
@Entity
@Table(name = "week_days")
@MappedEnum(enumClass = WeekDay.class)
public class WeekDayDict extends AbstractDictionary {
}
