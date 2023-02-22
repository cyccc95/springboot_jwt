package com.backend.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {
    ROLE_USER("USER", "사용자"),
    ROLE_ADMIN("ADMIN","관리자");

    private final String role;
    private final String description;

}
