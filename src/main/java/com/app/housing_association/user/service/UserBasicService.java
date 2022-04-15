package com.app.housing_association.user.service;

import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserBasicService extends AbstractCrudService<User, Long> implements UserService {

    public UserBasicService(UserRepository userRepository) {
        super(userRepository);
    }
}
