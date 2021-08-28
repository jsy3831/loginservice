package com.photobook.user.service.impl;

import com.photobook.user.mapper.UserMapper;
import com.photobook.user.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserMapper userMapper;

    public UserDto userInfo() {
        UserDto userInfo = new UserDto();
        userInfo.setUserId(1);
        userInfo.setId("admin");
        userInfo.setPassword("1234");
        userInfo.setName("관리자");
        userInfo.setEmail("admin@gmail.com");
        userInfo.setBirth(LocalDate.of(1993, 10, 28));
        userInfo.setProfileImageName("프로필사진1");
        userInfo.setProfileImagePath("이미지경로");
        userInfo.setProfileMessage("상태메세지");

        return userInfo;
    }

    @Test
    @DisplayName("사용자 정보가 존재하면 성공")
    public void successGetUserInfoByIdAndPassword() {
        //given
        UserDto userInfo = userInfo();
        when(userMapper.getUserInfoByIdAndPassword("admin", "1234")).thenReturn(userInfo);

        //when
        UserDto result = userServiceImpl.getUserInfoByIdAndPassword("admin", "1234");

        //then
        assertEquals(result, userInfo);
    }

    @Test
    @DisplayName("사용자 정보가 존재하지 않으면 성공")
    public void failureGetUserInfoByIdAndPassword() {
        //given
        when(userMapper.getUserInfoByIdAndPassword("notExistId", "notExistPassword")).thenReturn(null);

        //when
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    userServiceImpl.getUserInfoByIdAndPassword("notExistId", "notExistPassword");
                });

        //then
        assertEquals("아이디 또는 비밀번호가 잘못 입력 되었습니다.", exception.getMessage());

    }
}