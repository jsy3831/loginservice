package com.photobook.service;

import com.photobook.dto.UserDto;

public interface LoginService {

	void setLoginUserInfo(UserDto userDto);

	void removeLoginUserInfo();

	UserDto getLoginUserInfo();

}
