package com.app.housing_association.security.service;

import com.app.housing_association.security.utils.JwtUtils;
import com.app.housing_association.security.model.AppUserDetails;
import com.app.housing_association.security.model.LoggedUser;
import com.app.housing_association.security.model.LoginCredentials;
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

    public Authentication checkLoginCredentials(LoginCredentials credentials){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public LoggedUser getLoggedUser(Authentication authentication){
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        var x = jwtUtils.generateJwtToken(authentication);
        return new LoggedUser(
                userDetails.getUsername(),
                userDetails.getEmail(),
                x);
    }
}
