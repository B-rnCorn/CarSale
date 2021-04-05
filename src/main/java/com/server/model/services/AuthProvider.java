package com.server.model.services;

import com.server.model.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;


    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(email);

        if (user != null && user.getUsername().equals(email)) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Wrong password");
            }

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        } else
            throw new BadCredentialsException("Username not found");
    }

    public boolean supports(Class<?> arg) {
        return true;
    }
}
