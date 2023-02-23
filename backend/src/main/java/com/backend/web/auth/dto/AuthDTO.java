package com.backend.web.auth.dto;

import com.backend.common.model.type.UserType;
import lombok.Data;

public class AuthDTO {
    @Data
    public static class Create {
        private String loginId;
        private String password;
    }
}
