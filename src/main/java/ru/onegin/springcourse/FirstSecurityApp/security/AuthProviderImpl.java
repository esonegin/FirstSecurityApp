package ru.onegin.springcourse.FirstSecurityApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.onegin.springcourse.FirstSecurityApp.services.PersonDetailService;

import java.util.Collections;

/**
 * @author onegines
 * @date 07.10.2024
 */
@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final PersonDetailService personDetailService;

    @Autowired
    public AuthProviderImpl(PersonDetailService personDetailService) {
        this.personDetailService = personDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails personDetails = personDetailService.loadUserByUsername(username);
        String password= authentication.getCredentials().toString();
        if(!password.equals(personDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}