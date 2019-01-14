package ru.alvisid.pacs.model;

import ru.alvisid.pacs.model.abstractions.AbstractEntity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 *Represent the action types.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public enum ActionType {
    ENTER,
    EXIT,
    LOCK,
    UNLOCK
}
