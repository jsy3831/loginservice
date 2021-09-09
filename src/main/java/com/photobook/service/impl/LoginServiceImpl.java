package com.photobook.service.impl;

import com.photobook.dto.UserDto;
import com.photobook.service.LoginService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {

    private static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";

    private final HttpSession httpSession;

    public LoginServiceImpl(HttpSession httpSession) {
        this.httpSession = httpSession; // Scoped Proxy
    }

    @Override
    public void setLoginUserInfo(UserDto userDto) {
        httpSession.setAttribute(LOGIN_USER_INFO, userDto);
    }

    @Override
    public void removeLoginUserInfo() {
        httpSession.invalidate();
    }

    @Override
    public UserDto getLoginUserInfo() {
        return (UserDto) httpSession.getAttribute(LOGIN_USER_INFO);
    }
}

