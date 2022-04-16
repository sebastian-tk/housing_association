package com.app.housing_association.user.controller;

import com.app.housing_association.common.controller.GenericRestController;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.controller.mapper.UserMapperImpl;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends GenericRestController<User, UserDto, Long> {

    public UserController(UserService userService, UserMapperImpl userMapper) {
        super(userService, userMapper);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        String expression = "hello "
                            + SecurityContextHolder.getContext().getAuthentication().getName() + "\n" +
                            "You are logged :-)";
        return ResponseEntity.status(HttpStatus.OK)
                .body(expression);
    }
}
