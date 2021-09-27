package com.photobook.service.impl;

import com.photobook.mapper.UserMapper;
import com.photobook.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Mock
    PasswordEncoder passwordEncoder;

    public UserDto userInfo() {
        UserDto userInfo = new UserDto();
        userInfo.setUserId(1);
        userInfo.setId("admin");
        userInfo.setPassword(passwordEncoder.encode("1234"));
        userInfo.setName("관리자");
        userInfo.setEmail("admin@gmail.com");
        userInfo.setBirth(LocalDate.of(1993, 10, 28));
        userInfo.setProfileImageName("프로필사진1");
        userInfo.setProfileImagePath("이미지경로");
        userInfo.setProfileMessage("상태메세지");

        return userInfo;
    }

    @Test
    @DisplayName("로그인 검증 성공")
    public void successValidateLogin() {
        //given
        UserDto userInfo = userInfo();
        when(userMapper.getUserInfoById("admin")).thenReturn(userInfo);
        when(passwordEncoder.matches("1234", userInfo.getPassword())).thenReturn(true);

        //when
        UserDto result = userServiceImpl.validateLogin("admin", "1234");

        //then
        assertEquals(result, userInfo);
    }

    @Test
    @DisplayName("로그인 검증 실패_존재하지 않는 아이디")
    public void failureValidateLoginId() {
        //given
        when(userMapper.getUserInfoById("admin")).thenReturn(null);

        //then
        assertThrows(InternalAuthenticationServiceException.class, () -> {
            //when
            userServiceImpl.validateLogin("admin", "1234");
        });

    }

    @Test
    @DisplayName("로그인 검증 실패_비밀번호 불일치")
    public void failureValidateLoginPwd() {
        //given
        UserDto userInfo = userInfo();
        when(userMapper.getUserInfoById("admin")).thenReturn(userInfo);
        when(passwordEncoder.matches("1234", userInfo.getPassword())).thenReturn(false);

        //then
        assertThrows(BadCredentialsException.class, () -> {
            //when
            userServiceImpl.validateLogin("admin", "1234");
        });
    }

}