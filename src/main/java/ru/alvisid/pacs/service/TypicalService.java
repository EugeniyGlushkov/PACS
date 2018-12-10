package ru.alvisid.pacs.service;

import ru.alvisid.pacs.util.exceptions.NotFoundException;

import java.util.List;

public interface TypicalService<T> {
    T create(T obj);

    void update(T obj) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    T get(int id) throws NotFoundException;

    List<T> getAll();
}
