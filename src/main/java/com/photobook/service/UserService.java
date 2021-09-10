package com.photobook.service;

import com.photobook.dto.UserDto;

public interface UserService {

    UserDto getUserInfoByIdAndPassword(String id, String password);

    UserDto getUserInfoById(String id);

}
