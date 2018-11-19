package ru.alvisid.pacs.model.enumActivate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An annotation for mark a class which is mapped enumeration.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MappedEnum {
    /**
     * Returns the mapped enum class.
     *
     * @return the mapped enum class.
     */
    Class <? extends Enum> enumClass();
}
