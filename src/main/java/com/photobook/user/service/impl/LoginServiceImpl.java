package com.photobook.user.service.impl;

import com.photobook.user.dto.UserDto;
import com.photobook.user.service.LoginService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {

    private static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";

    @Override
    public void setLoginUserInfo(HttpSession httpSession, UserDto userDto) {
        httpSession.setAttribute(LOGIN_USER_INFO, userDto);
    }

    @Override
    public void removeLoginUserInfo(HttpSession httpSession) {
        httpSession.invalidate();
    }

    @Override
    public Object getLoginUserInfo(HttpSession httpSession) {
        return httpSession.getAttribute(LOGIN_USER_INFO);
    }
}
