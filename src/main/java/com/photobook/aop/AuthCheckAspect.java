package com.photobook.aop;

import com.photobook.user.service.LoginService;
import com.photobook.exception.UnauthorizedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Component
@Aspect
public class AuthCheckAspect {

    LoginService loginService;

    public AuthCheckAspect(LoginService loginService) {
        this.loginService = loginService;
    }

    @Before("@annotation(com.photobook.annotation.LoginCheck)")
    public void loginCheck() {

        HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()) // RequestAttributes가 없으면 예외 발생
                .getRequest()
                .getSession();

        if (loginService.getLoginUserInfo(httpSession) == null) {
            throw new UnauthorizedException("로그인된 사용자 정보가 존재하지 않습니다.");
        }

    }

}
