package com.app.housing_association.user.repository;

import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.entity.enums.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"contract","contract.flat","contract.fee"})
    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByUsernameIgnoreCase(String username);

    @Override
    @EntityGraph(attributePaths = {"contract"})
    List<User> findAll();

    List<User> findAllByRole(Role role);
}
