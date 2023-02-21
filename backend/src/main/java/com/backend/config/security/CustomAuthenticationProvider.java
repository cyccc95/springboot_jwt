package com.backend.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
// AuthenticationProvider를 implements 받아 인증처리 역할을 하는 핵심 클래스
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomLoadUserById customLoadUserById;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserPrincipal user = (UserPrincipal) customLoadUserById.loadUserByUsername(authentication.getName().toString());
        String reqPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(reqPassword, user.getPassword())) throw new BadCredentialsException("Not Found User");
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
