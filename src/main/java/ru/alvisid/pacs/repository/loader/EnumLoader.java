package ru.alvisid.pacs.repository.loader;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.alvisid.pacs.model.enumActivate.AbstractDictionary;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EnumLoader {
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        Set<EntityType<?>> entities = em.getMetamodel().getEntities();

        List<?> entityClasses = entities.stream()
                .map(EntityType::getJavaType)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        for (Object obj : entityClasses) {
            if (!AbstractDictionary.class.isAssignableFrom(obj.getClass())) {

            }
        }
    }

    private void updateEnumIdentifiers(Session session, Class<? extends Enum> enumClass,
                                       Class<? extends AbstractDictionary> dictClass) {

    }

    private void setEnumOrdinal(Enum object, int newOrdinal) {
        Field field;

        try {
            field = object.getClass().getSuperclass().getDeclaredField("ordinal");
            field.setAccessible(true);
            field.set(object, newOrdinal);
        } catch (Exception exc) {
            throw new RuntimeException("Can't update enum ordinal: " + exc);
        }
    }
}
