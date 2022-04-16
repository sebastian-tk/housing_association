package com.app.housing_association.security.registration;

import com.app.housing_association.security.registration.model.AdminRegistrationData;
import com.app.housing_association.security.registration.service.UserAdminService;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.controller.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/registration")
public class UserAdminRegistrationController {

    private final UserAdminService userAdminService;

    private final UserMapper userMapper;

    public UserAdminRegistrationController(UserAdminService userAdminService, UserMapper userMapper) {
        this.userAdminService = userAdminService;
        this.userMapper = userMapper;
    }

    @PostMapping()
    public ResponseEntity<UserDto> registration(@RequestBody AdminRegistrationData adminRegistrationData) {
        return Optional.of(adminRegistrationData)
                .flatMap(userAdminService::registration)
                .map(userMapper::toRegisteredDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseThrow();
    }
}
