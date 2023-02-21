package com.backend.config.security;

import com.backend.app.user.entity.User;
import com.backend.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
// UserDetailService를 implements 받아 실질적으로 회원정보를 조회하는 역할
public class CustomLoadUserById implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new UsernameNotFoundException("Not Found User");
        return UserPrincipal.create(user);
    }
}
