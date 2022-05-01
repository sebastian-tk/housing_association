package com.app.housing_association.security;

import com.app.housing_association.security.login.utils.JwtUtils;
import com.app.housing_association.security.login.model.AppUserDetails;
import com.app.housing_association.security.login.model.LoggedUser;
import com.app.housing_association.security.login.model.LoginCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public Authentication checkLoginCredentials(LoginCredentials credentials){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public LoggedUser getLoggedUser(Authentication authentication){
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        return new LoggedUser(
                userDetails.getUsername(),
                userDetails.getEmail(),
                jwtUtils.generateJwtToken(authentication));
    }
}
