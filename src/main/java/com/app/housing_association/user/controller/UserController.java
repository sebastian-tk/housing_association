package com.app.housing_association.user.controller;

import com.app.housing_association.common.controller.GenericRestController;
import com.app.housing_association.user.controller.dto.UserContractDto;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.controller.mapper.UserMapper;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends GenericRestController<User, UserDto, Long> {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper userMapper) {
        super(userService, userMapper);
        this.service = userService;
        this.mapper = userMapper;
    }

    @GetMapping("/{id}/contract")
    public ResponseEntity<UserContractDto> getByIdWithContract(@PathVariable Long id) {
        return service
                .findById(id)
                .map(mapper::toUserContractDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
