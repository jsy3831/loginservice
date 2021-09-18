package com.photobook.controller;

import com.photobook.annotation.LoginCheck;
import com.photobook.annotation.LoginUser;
import com.photobook.controller.response.Response;
import com.photobook.dto.UserDto;
import com.photobook.exception.customException.DuplicatedException;
import com.photobook.service.LoginService;
import com.photobook.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Response<UserDto> login(@RequestParam @NotBlank String id, @RequestParam @NotBlank String password,
                                   @LoginUser UserDto loginUser) {

        if(loginUser != null) {
            throw new DuplicatedException("이미 로그인된 상태입니다.");
        }

        UserDto userInfo = userService.validateLogin(id, password);

        loginService.setLoginUserInfo(userInfo);

        return Response.toResponse(200, "로그인 하였습니다.", userInfo);
    }

    @PostMapping("/logout")
    @LoginCheck
    public Response logout() {

        loginService.removeLoginUserInfo();

        return Response.toResponse(200, "로그아웃 하였습니다.");
    }

    @GetMapping("/my-info")
    @LoginCheck
    public Response<UserDto> getUserInfoById(@LoginUser UserDto loginUser) {

        UserDto userInfo = userService.getUserInfoById(loginUser.getId());

        return Response.toResponse(200, "회원정보 조회", userInfo);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signup(@RequestBody @Valid UserDto userInfo) {

        userService.createUser(userInfo);

        return Response.toResponse(201, "회원가입 하였습니다.");
    }

    @GetMapping("/{id}")
    public Response checkUserId(@PathVariable @NotBlank String id) {

        userService.validateUserId(id);

        return Response.toResponse(200, "사용가능한 아이디입니다.");
    }


    @DeleteMapping("/my-info")
    @LoginCheck
    public Response deleteUser(@LoginUser UserDto loginUser) {

        userService.deleteUser(loginUser.getId());

        loginService.removeLoginUserInfo();

        return Response.toResponse(200, "회원탈퇴 하였습니다.");
    }

}