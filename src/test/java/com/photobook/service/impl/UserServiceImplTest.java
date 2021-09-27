package com.photobook.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.photobook.dto.UserDto;
import com.photobook.exception.customexception.DuplicatedException;
import com.photobook.mapper.UserMapper;

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
		// userInfo.setPassword(passwordEncoder.encode("1234"));
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
	@DisplayName("로그인 검증 성공")
	public void successValidateLogin() {
		// given
		UserDto userInfo = userInfo();
		when(userMapper.getUserInfoById("admin")).thenReturn(userInfo);
		when(passwordEncoder.matches("1234", userInfo.getPassword())).thenReturn(true);

		// when
		UserDto result = userServiceImpl.validateLogin("admin", "1234");

		// then
		assertEquals(result, userInfo);
	}

	@Test
	@DisplayName("로그인 검증 실패_존재하지 않는 아이디")
	public void failureValidateLoginId() {
		// given
		when(userMapper.getUserInfoById("admin")).thenReturn(null);

		// then
		assertThrows(InternalAuthenticationServiceException.class, () -> {
			// when
			userServiceImpl.validateLogin("admin", "1234");
		});

	}

	@Test
	@DisplayName("로그인 검증 실패_비밀번호 불일치")
	public void failureValidateLoginPwd() {
		// given
		UserDto userInfo = userInfo();
		when(userMapper.getUserInfoById("admin")).thenReturn(userInfo);
		when(passwordEncoder.matches("1234", userInfo.getPassword())).thenReturn(false);

		// then
		assertThrows(BadCredentialsException.class, () -> {
			// when
			userServiceImpl.validateLogin("admin", "1234");
		});
	}

	@Test
	@DisplayName("아이디 검증 성공_사용가능한 아이디")
	public void successValidateUserId() {
		// given
		when(userMapper.checkUserId("admin")).thenReturn(false);

		// when
		userServiceImpl.validateUserId("admin");

		// then
		verify(userMapper, times(1)).checkUserId("admin");
	}

	@Test
	@DisplayName("아이디 검증 실패_중복된 아이디")
	public void failureValidateUserId() {
		// given
		when(userMapper.checkUserId("admin")).thenReturn(true);

		// then
		assertThrows(DuplicatedException.class, () -> {
			// when
			userServiceImpl.validateUserId("admin");
		});
	}

	@Test
	@DisplayName("회원가입 성공")
	public void successCreateUser() {
		// given
		UserDto userInfo = userInfo();
		when(userMapper.checkUserId(userInfo.getId())).thenReturn(false);
		doNothing().when(userMapper).createUser(userInfo);

		// when
		userServiceImpl.createUser(userInfo);

		// then
		verify(userMapper, times(1)).createUser(userInfo);
	}

	@Test
	@DisplayName("회원가입 실패_중복된 아이디")
	public void failureCreateUser() {
		// given
		UserDto userInfo = userInfo();
		when(userMapper.checkUserId(userInfo.getId())).thenReturn(true);

		// then
		assertThrows(DuplicatedException.class, () -> {
			// when
			userServiceImpl.createUser(userInfo);
		});

		// then
		verify(userMapper, times(0)).createUser(userInfo);
	}

	@Test
	@DisplayName("회원탈퇴 성공")
	public void successDeleteUser() {
		// given
		UserDto userInfo = userInfo();
		when(passwordEncoder.matches("1234", userInfo.getPassword())).thenReturn(true);
		doNothing().when(userMapper).deleteUser(userInfo.getId());

		// when
		userServiceImpl.deleteUser(userInfo, "1234");

		// then
		verify(userMapper, times(1)).deleteUser(userInfo.getId());
	}

	@Test
	@DisplayName("회원탈퇴 실패_비밀번호 불일치")
	public void failureDeleteUser() {
		// given
		UserDto userInfo = userInfo();
		when(passwordEncoder.matches("1234", userInfo.getPassword())).thenReturn(false);

		// then
		assertThrows(BadCredentialsException.class, () -> {
			// when
			userServiceImpl.deleteUser(userInfo, "1234");
		});

		// then
		verify(userMapper, times(0)).deleteUser(userInfo.getId());
	}

}