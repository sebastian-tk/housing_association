package com.app.housing_association.user.service;

import com.app.housing_association.common.service.abstracts.AbstractCrudService;
import com.app.housing_association.security.utils.PasswordUtils;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.entity.model.UserWithChangingPassword;
import com.app.housing_association.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.app.housing_association.common.utils.IValidation.*;
import static com.app.housing_association.user.entity.enums.Role.USER;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.util.Strings.*;

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
    public Optional<User> update(User input) {
        return userRepository
                .findById(input.getId())
                .map(user -> updateUser(user, input))
                .map(userRepository::save);
    }

    @Override
    public Optional<User> updateWithChangePassword(UserWithChangingPassword input) {
        return userRepository
                .findById(input.getId())
                .map(user -> updateUserWithChangingPassword(user, input))
                .map(userRepository::save);
    }


    @Override
    public User save(User user) {
        if (isNull(user)) {
            throw new IllegalArgumentException(USER_NULL_VALIDATION);
        }
        user.setRole(USER);
        user.setPassword(passwordUtils.encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }

    private User updateUser(User existUser, User input) {
        updateBaseFieldsUser(existUser, input);
        existUser.setPassword(
                nonNull(input.getPassword())
                ? passwordUtils.encryptPassword(input.getPassword())
                : existUser.getPassword());
        return existUser;
    }

    private User updateUserWithChangingPassword(User existUser, UserWithChangingPassword input) {
        updateBaseFieldsUser(existUser, input);
        existUser.setPassword(
                nonNull(input.getPassword())
                        ? checkAndGetHashNewPassword(existUser, input.getPassword(), input.getNewPassword())
                        : existUser.getPassword());
        return existUser;
    }

    private void updateBaseFieldsUser(User existUser, User input) {
        existUser.setFirstname(nonNull(input.getFirstname()) ? input.getFirstname() : existUser.getFirstname());
        existUser.setLastname(nonNull(input.getLastname()) ? input.getLastname() : existUser.getLastname());
        existUser.setUsername(nonNull(input.getUsername()) ? input.getUsername() : existUser.getUsername());
        existUser.setPhoneNumber(nonNull(input.getPhoneNumber()) ? input.getPhoneNumber() : existUser.getPhoneNumber());
        existUser.setRole(USER);
        existUser.setEmail(nonNull(input.getEmail()) ? input.getEmail() : existUser.getEmail());
    }

    private String checkAndGetHashNewPassword(User existUser, String password, String newPassword) {
        if (isBlank(password)) {
            throw new IllegalArgumentException(USER_OLD_PASSWORD_VALIDATION);
        }
        if (isBlank(newPassword)) {
            throw new IllegalArgumentException(USER_NEW_PASSWORD_VALIDATION);
        }
        if (whetherPasswordsDoNotMatch(existUser.getPassword(), password)) {
            throw new IllegalArgumentException(USER_PASSWORD_CORRECT);
        }
        return passwordUtils.encryptPassword(newPassword);
    }

    private boolean whetherPasswordsDoNotMatch(String existHash, String password) {
        return !passwordUtils.isMatchesPassword(password, existHash);
    }
}

