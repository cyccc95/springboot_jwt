package com.backend.app.user.dto;

import com.backend.common.model.UserRole;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDTO {
    private String userId;
    private String pw;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
