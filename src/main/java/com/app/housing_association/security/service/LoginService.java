package com.app.housing_association.security.service;

import com.app.housing_association.security.model.AppUserDetails;
import com.app.housing_association.security.model.LoggedUser;
import com.app.housing_association.security.model.LoginCredentials;
import com.app.housing_association.security.utils.JwtUtils;
import com.app.housing_association.user.entity.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public Authentication checkLoginCredentials(LoginCredentials credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public LoggedUser getLoggedUser(Authentication authentication) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        return new LoggedUser(
                userDetails.getId(),
                userDetails.getUsername(),
                getRole(userDetails),
                jwtUtils.generateJwtToken(authentication));
    }

    private Role getRole(AppUserDetails userDetails) {
        return Role.valueOf(
                userDetails
                        .getAuthorities()
                        .stream()
                        .toList()
                        .get(0)
                        .toString()
        );
    }
}
