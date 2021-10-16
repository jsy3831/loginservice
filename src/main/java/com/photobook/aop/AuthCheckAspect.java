package com.photobook.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.photobook.dto.UserDto;
import com.photobook.exception.customexception.UnauthorizedException;
import com.photobook.service.LoginService;

@Component
@Aspect
public class AuthCheckAspect {

	private final LoginService loginService;

	public AuthCheckAspect(LoginService loginService) {
		this.loginService = loginService;
	}

	@Before("@annotation(com.photobook.annotation.LoginCheck)")
	public void loginCheck() {

		UserDto userInfo = loginService.getLoginUserInfo();

		if (userInfo == null) {
			throw new UnauthorizedException("로그인된 사용자 정보가 존재하지 않습니다.");
		}
	}

}
