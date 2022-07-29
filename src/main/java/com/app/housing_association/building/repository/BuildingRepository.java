package com.app.housing_association.building.repository;

import com.app.housing_association.building.entity.Building;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"flats"})
    List<Building> findAll();
}
