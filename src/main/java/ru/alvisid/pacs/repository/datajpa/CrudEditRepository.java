package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.Edit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CrudEditRepository extends JpaRepository <Edit, Integer> {
    @Transactional
    @Override
    Edit save(Edit edit);

    @Transactional
    @Modifying
    @Query("DELETE FROM Edit e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional <Edit> findById(Integer integer);

    @Override
    List <Edit> findAll(Sort sort);

    @Query("SELECT e FROM Edit e WHERE e.employee.id=:empId")
    List <Edit> findAllByEmployeeId(@Param("empId") int empId, Sort sort);

    @Query("SELECT e FROM Edit e WHERE e.editDateTime>=:start AND e.editDateTime<=:end")
    List <Edit> findAllBetween(@Param("start") LocalDateTime start,
                               @Param("end") LocalDateTime end,
                               Sort sort);
}
