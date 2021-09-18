package com.photobook.service.impl;

import com.photobook.exception.customException.DuplicatedException;
import com.photobook.mapper.UserMapper;
import com.photobook.dto.UserDto;
import com.photobook.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto validateLogin(String id, String password) {

        UserDto userInfo = userMapper.getUserInfoById(id);

        if(userInfo == null) {
            throw new InternalAuthenticationServiceException("존재하지않는 아이디입니다.");
        }

        boolean isPwdMatch = passwordEncoder.matches(password, userInfo.getPassword());

        if(!isPwdMatch) {
            throw new BadCredentialsException("일치하지않는 비밀번호입니다.");
        }

        return userInfo;
    }

    @Override
    public UserDto getUserInfoById(String id) {
        UserDto userInfo = userMapper.getUserInfoById(id);
        return userInfo;
    }

    @Override
    public void createUser(UserDto userInfo) {
        validateUserId(userInfo.getId());

        String encodedPwd = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encodedPwd);

        userMapper.createUser(userInfo);
    }

    @Override
    public void validateUserId(String id) {
        if(userMapper.checkUserId(id)) {
            throw new DuplicatedException("중복된 아이디입니다.");
        }
    }

    @Override
    public void deleteUser(String id) {
        userMapper.deleteUser(id);
    }
}
