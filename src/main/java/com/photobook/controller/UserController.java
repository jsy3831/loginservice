package com.photobook.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.photobook.annotation.LoginCheck;
import com.photobook.annotation.LoginUser;
import com.photobook.controller.response.Response;
import com.photobook.dto.UserDto;
import com.photobook.exception.customexception.DuplicatedException;
import com.photobook.service.LoginService;
import com.photobook.service.UserService;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

	private final UserService userService;

	private final LoginService loginService;

	public UserController(UserService userService, LoginService loginService) {
		this.userService = userService;
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public Response<UserDto> login(@RequestParam @NotBlank String id, @RequestParam @NotBlank String password,
		@LoginUser UserDto loginUser) {

		if (loginUser != null) {
			throw new DuplicatedException("이미 로그인된 상태입니다.");
		}

		UserDto userInfo = userService.validateLogin(id, password);

		loginService.setLoginUserInfo(userInfo);

		return new Response("로그인 하였습니다.", userInfo);
	}

	@PostMapping("/logout")
	@LoginCheck
	public Response logout() {

		loginService.removeLoginUserInfo();

		return new Response("로그아웃 하였습니다.");
	}

	@GetMapping("/my-info")
	@LoginCheck
	public Response<UserDto> getUserInfoById(@LoginUser UserDto loginUser) {

		UserDto userInfo = userService.getUserInfoById(loginUser.getId());

		return new Response("회원정보 조회", userInfo);
	}

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public Response signup(@RequestBody @Valid UserDto userInfo) {

		userService.createUser(userInfo);

		return new Response("회원가입 하였습니다.");
	}

	@GetMapping("/{id}")
	public Response checkUserId(@PathVariable @NotBlank String id) {

		userService.validateUserId(id);

		return new Response("사용가능한 아이디입니다.");
	}

	@DeleteMapping("/my-info")
	@LoginCheck
	public Response deleteUser(@RequestParam @NotBlank String password, @LoginUser UserDto loginUser) {

		userService.deleteUser(loginUser, password);

		loginService.removeLoginUserInfo();

		return new Response("회원탈퇴 하였습니다.");
	}

}