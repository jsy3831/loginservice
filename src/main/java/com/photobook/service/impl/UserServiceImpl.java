package com.photobook.service.impl;

import com.photobook.mapper.UserMapper;
import com.photobook.dto.UserDto;
import com.photobook.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getUserInfoByIdAndPassword(String id, String password) {

        UserDto userInfo = userMapper.getUserInfoByIdAndPassword(id, password);

        Assert.notNull(userInfo, "아이디 또는 비밀번호가 잘못 입력 되었습니다.");

        return userInfo;
    }

    @Override
    public UserDto getUserInfoById(String id) {
        UserDto userInfo = userMapper.getUserInfoById(id);
        return userInfo;
    }
}
