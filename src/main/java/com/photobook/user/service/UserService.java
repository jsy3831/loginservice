package com.photobook.user.service;

import com.photobook.user.dto.UserDto;

public interface UserService {

    UserDto getUserInfoByIdAndPassword(String id, String password);

    UserDto getUserInfoById(String id);

}
