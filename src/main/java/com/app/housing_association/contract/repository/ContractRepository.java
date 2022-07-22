package com.app.housing_association.contract.repository;

import com.app.housing_association.contract.entity.Contract;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Override
    @EntityGraph(attributePaths = {"fee","flat","user"})
    List<Contract> findAll();
}
