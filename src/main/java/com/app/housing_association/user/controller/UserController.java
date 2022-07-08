package com.app.housing_association.user.controller;

import com.app.housing_association.common.controller.GenericRestController;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.controller.mapper.UserMapperImpl;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends GenericRestController<User, UserDto, Long> {

    public UserController(UserService userService, UserMapperImpl userMapper) {
        super(userService, userMapper);
    }
}
