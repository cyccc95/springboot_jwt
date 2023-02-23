package com.backend.web.user.repository;

import com.backend.web.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginId(String loginId);
    User findByIdx(Long idx);
}
