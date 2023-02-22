package com.backend.app.user.repository;

import com.backend.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginId(String loginId);
    User findByIdx(Long idx);
}
