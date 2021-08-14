package com.sns.untitled.user.service.impl;

import com.sns.untitled.user.mapper.UserMapper;
import com.sns.untitled.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public String hello(String name) {
        userMapper.insertName(name);
        return "Hello " + name + " Success Insert";
    }
}
