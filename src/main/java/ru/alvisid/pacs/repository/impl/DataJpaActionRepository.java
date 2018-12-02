package ru.alvisid.pacs.repository.impl;

import ru.alvisid.pacs.model.Action;
import ru.alvisid.pacs.repository.ActionRepository;

import java.time.LocalDateTime;
import java.util.List;

public class DataJpaActionRepository implements ActionRepository {

    @Override
    public Action save(Action obj) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Action get(int id) {
        return null;
    }

    @Override
    public List<Action> getAll() {
        return null;
    }
    @Override
    public List<Action> getAllByEmplId(int id) {
        return null;
    }

    @Override
    public List<Action> getAllBetween(LocalDateTime start, LocalDateTime end) {
        return null;
    }
}
