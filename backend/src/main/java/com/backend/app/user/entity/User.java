package com.backend.app.user.entity;

import com.backend.app.user.dto.UserDTO;
import com.backend.common.model.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String userId;
    private String pw;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    public User(
            String userId,
            String pw,
            String email
    ) {
        this.userId = userId;
        this.pw = pw;
        this.email = email;
    }

    public UserDTO toUserDTO() {
        return new ModelMapper().map(this, UserDTO.class);
    }
}
