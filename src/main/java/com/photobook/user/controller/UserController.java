package com.photobook.user.controller;

import com.photobook.annotation.LoginCheck;
import com.photobook.user.dto.UserDto;
import com.photobook.user.service.LoginService;
import com.photobook.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public void login(@RequestParam @NotBlank String id, @RequestParam @NotBlank String password, HttpSession httpSession) {
        UserDto userInfo = userService.getUserInfoByIdAndPassword(id, password);

        loginService.setLoginUserInfo(httpSession, userInfo);
    }

    @PostMapping("/logout")
    @LoginCheck
    public void logout(HttpSession httpSession) {
        loginService.removeLoginUserInfo(httpSession);
    }

    @GetMapping("/{id}")
    @LoginCheck
    public UserDto getUserInfoById(@PathVariable @NotBlank String id) {
        UserDto userInfo = userService.getUserInfoById(id);
        return userInfo;
    }
}