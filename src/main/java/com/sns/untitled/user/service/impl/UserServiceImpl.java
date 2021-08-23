package com.sns.untitled.user.service.impl;

import com.sns.untitled.user.mapper.UserMapper;
import com.sns.untitled.user.dto.UserDto;
import com.sns.untitled.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserDto login(String id, String password) {
        UserDto userInfo = userMapper.findUserByIdAndPassword(id, password);
        return userInfo;
    }

    @Override
    public UserDto getUserInfo(String id) {
        UserDto userInfo = userMapper.findUserById(id);
        return userInfo;
    }
}
