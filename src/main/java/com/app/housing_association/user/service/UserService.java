package com.app.housing_association.user.service;

import com.app.housing_association.common.service.abstracts.CrudService;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.entity.enums.Role;
import com.app.housing_association.user.entity.model.UserWithChangingPassword;
import com.app.housing_association.vote.entity.Vote;

import java.util.List;
import java.util.Optional;

public interface UserService extends CrudService<User, Long> {

    boolean canNotHaveContract(User user);

    Optional<User> updateWithChangePassword(UserWithChangingPassword input);

    List<User> findAllByRoleOrAll(Role role);

    User getByIdToAddVote(Long id, Vote vote);
}
