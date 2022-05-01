package com.app.housing_association.security;

import com.app.housing_association.security.login.model.LoginCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginCredentials loginCredentials) {
        Authentication authentication = authService.checkLoginCredentials(loginCredentials);
        return ResponseEntity.ok(authService.getLoggedUser(authentication));
    }

}
