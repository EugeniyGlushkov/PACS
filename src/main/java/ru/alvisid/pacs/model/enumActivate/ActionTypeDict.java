package ru.alvisid.pacs.model.enumActivate;

import ru.alvisid.pacs.model.ActionType;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * An action type dictionary from the edit_types dictionary in the data base.
 */
@Entity
@Table(name = "action_types")
@MappedEnum(enumClass = ActionType.class)
public class ActionTypeDict extends AbstractDictionary {
}
