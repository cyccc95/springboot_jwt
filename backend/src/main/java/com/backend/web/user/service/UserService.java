package com.backend.web.user.service;

import com.backend.web.user.dto.UserDTO;
import com.backend.web.user.entity.User;
import com.backend.web.user.repository.UserRepository;
import com.backend.common.model.CustomException;
import com.backend.common.model.StatusCode;
import com.backend.common.model.type.UserType;
import com.backend.common.util.CheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserDTO.Create userInfo) throws CustomException {
        if (CheckUtil.isEmptyString(userInfo.getLoginId())) {
            throw new CustomException(StatusCode.CODE_601);
        } else if (CheckUtil.isEmptyString(userInfo.getNickname())) {
            throw new CustomException(StatusCode.CODE_602);
        } else if (CheckUtil.isEmptyString(userInfo.getPassword())) {
            throw new CustomException(StatusCode.CODE_603);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .loginId(userInfo.getLoginId())
                .nickname(userInfo.getNickname())
                .password(passwordEncoder.encode(userInfo.getPassword()))
                .userType(UserType.USER)
                .build();
        user.setCreateAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());
        userRepository.save(user).toUserBasicDTO();
    }

}
