package com.photobook.service;

import com.photobook.dto.UserDto;

public interface UserService {

    UserDto validateLogin(String id, String password);

    UserDto getUserInfoById(String id);

    void createUser(UserDto userInfo);

    void validateUserId(String id);

    void deleteUser(String id);
}
