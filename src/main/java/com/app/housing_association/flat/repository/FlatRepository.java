package com.app.housing_association.flat.repository;

import com.app.housing_association.flat.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {

    List<Flat> findByAvailableIsTrue();

}
