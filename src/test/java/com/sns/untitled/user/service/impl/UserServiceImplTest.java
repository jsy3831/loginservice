package com.sns.untitled.user.service.impl;

import com.sns.untitled.user.mapper.UserMapper;
import com.sns.untitled.user.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserMapper userMapper;

    public UserDto userDto() {
        UserDto user = new UserDto();
        user.setUserId(1);
        user.setId("admin");
        user.setPassword("1234");
        user.setName("관리자");
        user.setEmail("admin@gmail.com");
        user.setProfileImageName("프로필사진1");
        user.setProfileImagePath("이미지경로");
        user.setProfileMessage("상태메세지");
        return user;
    }

    @Test
    @DisplayName("로그인 성공")
    public void successLogin() throws Exception {
        //given
        UserDto userDto = userDto();
        when(userMapper.findUserByIdAndPassword("admin", "1234")).thenReturn(userDto);

        //when
        UserDto result = userServiceImpl.login("admin", "1234");

        //then
        assertEquals(result, userDto);
    }
}