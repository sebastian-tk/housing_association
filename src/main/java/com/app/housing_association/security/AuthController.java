package com.app.housing_association.security;

import com.app.housing_association.security.model.LoggedUser;
import com.app.housing_association.security.model.LoginCredentials;
import com.app.housing_association.security.model.AdminRegistrationData;
import com.app.housing_association.security.service.LoginService;
import com.app.housing_association.security.service.RegistrationService;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.controller.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final LoginService loginService;

    private final RegistrationService registrationService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<LoggedUser> authenticateUser(@Valid @RequestBody LoginCredentials loginCredentials) {
        Authentication authentication = loginService.checkLoginCredentials(loginCredentials);
        return ResponseEntity.ok(loginService.getLoggedUser(authentication));
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody AdminRegistrationData adminRegistrationData) {
        return Optional.of(adminRegistrationData)
                .flatMap(registrationService::registration)
                .map(userMapper::toRegisteredDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseThrow();
    }

}
