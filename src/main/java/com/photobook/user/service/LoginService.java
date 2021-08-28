package com.photobook.user.service;

import com.photobook.user.dto.UserDto;

import javax.servlet.http.HttpSession;

public interface LoginService {

    void setLoginUserInfo(HttpSession httpSession, UserDto userDto);

    void removeLoginUserInfo(HttpSession httpSession);

    Object getLoginUserInfo(HttpSession httpSession);

}
