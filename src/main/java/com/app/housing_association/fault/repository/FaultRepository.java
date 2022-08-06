package com.app.housing_association.fault.repository;

import com.app.housing_association.fault.entity.Fault;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaultRepository extends JpaRepository<Fault, Long> {

    @Override
    @EntityGraph(attributePaths = {"building"})
    List<Fault> findAll();

    @EntityGraph(attributePaths = {"building"})
    List<Fault> findAllByFixed(boolean fixed);
}
