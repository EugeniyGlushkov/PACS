package ru.alvisid.pacs.repository;

import ru.alvisid.pacs.model.Absence;

import java.util.List;

public interface AbsenceRepository {
    Absence save(Absence absence);

    boolean delete (int id);

    Absence get (int id);

    List<Absence> getAll();
}
