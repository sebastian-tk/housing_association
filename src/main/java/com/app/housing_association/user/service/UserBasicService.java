package com.app.housing_association.user.service;

import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UserBasicService extends AbstractCrudService<User, Long> implements UserService {

    private final UserRepository userRepository;

    public UserBasicService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public boolean canNotHaveContract(User user) {
        if(isNull(user)){
            throw new IllegalArgumentException("User cannot be null");
        }
        if(isNull(user.getId())){
            return false;
        }
         return userRepository
                 .findById(user.getId())
                 .map(foundUser-> nonNull(foundUser.getContract()))
                 .orElse(false);
    }
}
