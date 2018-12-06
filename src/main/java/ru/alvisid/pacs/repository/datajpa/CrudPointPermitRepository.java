package ru.alvisid.pacs.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.pacs.model.PointPermit;

import java.util.List;
import java.util.Optional;

public interface CrudPointPermitRepository extends JpaRepository<PointPermit, Integer> {
    @Transactional
    @Override
    PointPermit save(PointPermit pointPermit);

    @Transactional
    @Modifying
    @Query
    int delete(@Param("id") int id);

    @Override
    Optional<PointPermit> findById(Integer integer);

    @Override
    List<PointPermit> findAll(Sort sort);

    @Query("SELECT pp FROM PointPermit pp WHERE pp.employee.id=:empId " +
            "ORDER BY pp.pointAction.controlPoint.serialCode, pp.pointAction.actionType.type")
    List<PointPermit> findAllByEmployeeId(@Param("empId") int empId);

    @Query("SELECT pp FROM PointPermit pp WHERE pp.pointAction.controlPoint.id=:cPointId")
    List<PointPermit> findAllByControlPointId(@Param("cPointId") int cPointId, Sort sort);
}
