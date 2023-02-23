package com.backend.web.auth.entity;

import com.backend.web.user.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class Auth implements Serializable {
    private Long idx;
    private User user;
}
