package com.photobook.service;

import com.photobook.dto.UserDto;

import javax.servlet.http.HttpSession;

public interface LoginService {

    void setLoginUserInfo(UserDto userDto);

    void removeLoginUserInfo();

    UserDto getLoginUserInfo();

}
