package com.app.housing_association.notice.repository;

import com.app.housing_association.notice.entity.Notice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @EntityGraph(attributePaths = {"building"})
    @Override
    List<Notice> findAll();

    @EntityGraph(attributePaths = {"building"})
    List<Notice> findAllByArchived(Boolean archived);
}
