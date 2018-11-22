package ru.alvisid.pacs;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
public class Cont {
    @PersistenceContext
    EntityManager em;

    public EntityManager getEm() {
        return em;
    }
}
