package com.photobook.controller;

import com.photobook.annotation.LoginCheck;
import com.photobook.annotation.LoginUser;
import com.photobook.controller.response.Response;
import com.photobook.dto.UserDto;
import com.photobook.exception.customException.DuplicatedException;
import com.photobook.service.LoginService;
import com.photobook.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

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
    public Response<UserDto> login(@RequestParam @NotBlank String id, @RequestParam @NotBlank String password) {

        if(loginService.getLoginUserInfo() != null) {
            throw new DuplicatedException("이미 로그인된 상태입니다.");
        }

        UserDto userInfo = userService.getUserInfoByIdAndPassword(id, password);

        loginService.setLoginUserInfo(userInfo);

        return Response.toResponse(200, "로그인 하였습니다.", userInfo);
    }

    @PostMapping("/logout")
    @LoginCheck
    public Response<UserDto> logout() {
        loginService.removeLoginUserInfo();

        return Response.toResponse(200, "로그아웃 하였습니다.");
    }

    @GetMapping("/my-info")
    @LoginCheck
    public Response<UserDto> getUserInfoById(@LoginUser String id) {

        UserDto userInfo = userService.getUserInfoById(id);

        return Response.toResponse(200, "회원정보 조회", userInfo);
    }

}