package com.backend.app.user.entity;

import com.backend.app.user.dto.UserDTO;
import com.backend.common.model.DateAudit;
import com.backend.common.model.UserType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String loginId;
    private String nickname;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder
    public User(String loginId, String nickname, String password, UserType userType) {
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
        this.userType = userType;
    }

    public UserDTO.Info toUserInfoDTO() {
        return new ModelMapper().map(this, UserDTO.Info.class);
    }
}
