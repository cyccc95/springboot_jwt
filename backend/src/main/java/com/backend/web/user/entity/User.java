package com.backend.web.user.entity;

import com.backend.web.user.dto.UserDTO;
import com.backend.common.model.DateAudit;
import com.backend.common.model.type.UserType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

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
    public User(Long idx, String loginId, String nickname, String password, UserType userType) {
        this.idx = idx;
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
        this.userType = userType;
    }

    public UserDTO.Basic toUserBasicDTO() {
        return new ModelMapper().map(this, UserDTO.Basic.class);
    }
}
