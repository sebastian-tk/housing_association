package com.app.housing_association.vote.repository;

import com.app.housing_association.vote.entity.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Override
    @EntityGraph(attributePaths = {"users"})
    List<Vote> findAll();

    @EntityGraph(attributePaths = {"users"})
    List<Vote> findAllByFinished(boolean finished);
}
