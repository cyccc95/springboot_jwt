package com.backend.web.user.service;

import com.backend.web.user.entity.User;
import com.backend.web.user.repository.UserRepository;
import com.backend.config.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(loginId);
        return user == null ? null : UserPrincipal.create(user);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByIdx(Long idx) {
        User user = userRepository.findByIdx(idx);
        return user == null ? null : UserPrincipal.create(user);
    }
}
