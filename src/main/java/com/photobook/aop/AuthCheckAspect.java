package com.photobook.aop;

import com.photobook.annotation.LoginCheck;
import com.photobook.exception.UnauthorizedException;
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

//    @Before("@annotation(com.photobook.annotation.LoginCheck)")
//    public void loginCheck() {
//
//        UserDto userInfo = loginService.getLoginUserInfo();
//
//        if(userInfo == null) {
//            throw new UnauthorizedException("로그인된 사용자 정보가 존재하지 않습니다.");
//        }
//
//    }

    @Around("@annotation(com.photobook.annotation.LoginCheck)")
    public Object loginCheck(ProceedingJoinPoint pjp) throws Throwable {

        UserDto userInfo = loginService.getLoginUserInfo();
//
//        if(userInfo == null) {
//            throw new UnauthorizedException("로그인된 사용자 정보가 존재하지 않습니다.");
//        }
        System.out.println("어라운드 전");

        Object result = pjp.proceed(new Object[] {userInfo});
        System.out.println("aspet" + result);
        return result;
//        System.out.println("어라운드 후");

    }

}
