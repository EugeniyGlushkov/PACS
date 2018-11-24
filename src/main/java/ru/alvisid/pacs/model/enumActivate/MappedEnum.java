package ru.alvisid.pacs.model.enumActivate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An annotation for mark a mapped class which is represented by enumeration class.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MappedEnum {
    /**
     * Returns the enum class which presents the marked by this annotation class.
     *
     * @return the mapped enum class which presents the marked by this annotation class.
     */
    Class <? extends Enum> enumClass();
}
