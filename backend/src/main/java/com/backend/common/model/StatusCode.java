package com.backend.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusCode {
    CODE_200(200, "success"),
    CODE_400(400, "Bad Request"),
    CODE_500(500, "Internal Server Error"),

    /* login */
    CODE_601(601, "로그인 아이디를 입력해주세요."),
    CODE_602(602, "비밀번호를 입력해주세요."),
    CODE_603(603, "유저 정보가 존재하지 않습니다."),
    CODE_604(604, "로그인 정보가 일치하지 않습니다.");

    private final Integer code;
    private final String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
