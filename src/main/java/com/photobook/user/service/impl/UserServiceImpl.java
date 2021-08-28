package com.photobook.user.service.impl;

import com.photobook.user.mapper.UserMapper;
import com.photobook.user.dto.UserDto;
import com.photobook.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getUserInfoByIdAndPassword(String id, String password) {

        UserDto userInfo = userMapper.getUserInfoByIdAndPassword(id, password);

        if(userInfo == null) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 잘못 입력 되었습니다.");
        }

        return userInfo;
    }

    @Override
    public UserDto getUserInfoById(String id) {
        UserDto userInfo = userMapper.getUserInfoById(id);
        return userInfo;
    }
}
