package com.backend.app.user.dto;

import com.backend.common.model.UserType;
import lombok.Data;

import java.time.LocalDateTime;

public class UserDTO {

    @Data
    public static class Info {
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
    public static class Update {
        private String loginId;
        private String nickname;
        private String password;
    }
}
