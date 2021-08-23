package com.sns.untitled.user.service;

import com.sns.untitled.user.dto.UserDto;

public interface UserService {

    UserDto login(String id, String password);

    UserDto getUserInfo(String id);

}
