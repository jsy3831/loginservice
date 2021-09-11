package com.photobook.controller;

import com.photobook.annotation.LoginCheck;
import com.photobook.annotation.LoginCheckAndReturnUserInfo;
import com.photobook.dto.UserDto;
import com.photobook.exception.CustomException;
import com.photobook.exception.ErrorCode;
import com.photobook.service.LoginService;
import com.photobook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

        if(loginService.getLoginUserInfo() != null) {
            throw new CustomException(ErrorCode.DUPLICATE_LOGIN);
        }

        UserDto userInfo = userService.getUserInfoByIdAndPassword(id, password);

        loginService.setLoginUserInfo(userInfo);
    }

    @PostMapping("/logout")
    @LoginCheck
    public void logout() {
        loginService.removeLoginUserInfo();
    }

    @GetMapping("/myInfo")
    @LoginCheckAndReturnUserInfo
    public ResponseEntity<UserDto> getUserInfoById(@ModelAttribute @NotNull UserDto userDto) {

        String id = userDto.getId(); // Aspect에서 받아온 값

        UserDto userInfo = userService.getUserInfoById(id);

        return ResponseEntity.ok(userInfo);
    }

}