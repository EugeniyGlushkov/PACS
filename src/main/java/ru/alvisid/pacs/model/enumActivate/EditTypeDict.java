package ru.alvisid.pacs.model.enumActivate;

import ru.alvisid.pacs.model.EditType;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * An edit type dictionary from the edit_types dictionary in the data base.
 */
@Entity
@Table(name = "edit_types")
@MappedEnum(enumClass = EditType.class)
public class EditTypeDict extends AbstractDictionary {
}
