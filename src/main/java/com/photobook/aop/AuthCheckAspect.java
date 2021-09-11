package com.photobook.aop;

import com.photobook.exception.CustomException;
import com.photobook.exception.ErrorCode;
import com.photobook.dto.UserDto;
import com.photobook.service.LoginService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthCheckAspect {

    private final LoginService loginService;

    public AuthCheckAspect(LoginService loginService) {
        this.loginService = loginService;
    }

    @Before("@annotation(com.photobook.annotation.LoginCheck)")
    public UserDto loginCheck() {

        UserDto userInfo = loginService.getLoginUserInfo();

        if(userInfo == null) {
            throw new CustomException(ErrorCode.NOT_LOGIN);
        }

        return userInfo;
    }

    @Around("@annotation(com.photobook.annotation.LoginCheckAndReturnUserInfo)")
    public Object loginCheckAndReturnUserInfo(ProceedingJoinPoint pjp) throws Throwable {

        UserDto userInfo = loginCheck();

        Object result = pjp.proceed(new Object[] { userInfo });

        return result;
    }

}
