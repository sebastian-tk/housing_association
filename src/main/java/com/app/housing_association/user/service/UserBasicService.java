package com.app.housing_association.user.service;

import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.security.utils.PasswordUtils;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import static com.app.housing_association.common.utils.IValidation.USER_NULL_VALIDATION;
import static com.app.housing_association.user.entity.enums.Role.USER;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UserBasicService extends AbstractCrudService<User, Long> implements UserService {

    private final UserRepository userRepository;

    private final PasswordUtils passwordUtils;

    public UserBasicService(UserRepository userRepository, PasswordUtils passwordUtils) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordUtils = passwordUtils;
    }

    @Override
    public boolean canNotHaveContract(User user) {
        if (isNull(user)) {
            throw new IllegalArgumentException(USER_NULL_VALIDATION);
        }
        if (isNull(user.getId())) {
            return false;
        }
        return userRepository
                .findById(user.getId())
                .map(foundUser -> nonNull(foundUser.getContract()))
                .orElse(false);
    }

    @Override
    public User save(User user) {
        if (isNull(user)) {
            throw new IllegalArgumentException(USER_NULL_VALIDATION);
        }
        User createdUser = new User();
        createdUser.setUsername(user.getUsername());
        createdUser.setEmail(user.getEmail());
        createdUser.setRole(USER);
        createdUser.setPassword(passwordUtils.encryptPassword(user.getPassword()));
        return super.save(createdUser);
    }
}
