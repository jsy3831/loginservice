package com.photobook.controller;

import com.photobook.annotation.LoginCheck;
import com.photobook.dto.UserDto;
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
    public void login(@RequestParam @NotBlank String id, @RequestParam @NotBlank String password) {
        UserDto userInfo = userService.getUserInfoByIdAndPassword(id, password);

        loginService.setLoginUserInfo(userInfo);
    }

    @PostMapping("/logout")
    @LoginCheck
    public void logout() {
        loginService.removeLoginUserInfo();
    }

    @GetMapping("/{id}")
    @LoginCheck
    public UserDto getUserInfoById(@PathVariable @NotBlank String id) {
        UserDto userInfo = userService.getUserInfoById(id);
        return userInfo;
    }
}