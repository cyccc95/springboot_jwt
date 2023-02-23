package com.backend.web.user.dto;

import com.backend.common.model.type.UserType;
import lombok.Data;

import java.time.LocalDateTime;

public class UserDTO {

    @Data
    public static class Basic {
        private Long idx;
        private String loginId;
        private String nickname;
        private String password;
        private UserType userType;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }

    @Data
    public static class Create {
        private String loginId;
        private String nickname;
        private String password;
    }

    @Data
    public static class Simple {
        private String loginId;
        private String nickname;
    }
}
