package com.photobook.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 401 UNAUTHORIZED */
    NOT_LOGIN(HttpStatus.UNAUTHORIZED, "로그인된 사용자 정보가 존재하지 않습니다."),

    /* 409 CONFLICT */
    DUPLICATE_LOGIN(HttpStatus.CONFLICT, "이미 로그인된 상태입니다.")

    ;

    private final HttpStatus httpStatus;
    private final String message;
}
