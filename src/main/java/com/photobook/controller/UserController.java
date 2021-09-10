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

//    @PostMapping("/logout")
//    @LoginCheck
//    public void logout() {
//        loginService.removeLoginUserInfo();
//    }

    /*
    * 현재 session에 user entity가 다 있기때문에 DB에서 안가져와도 됨
    * session에 user entity를 모두 가지고 있는것 vs 필요에 따라 그때그때 DB에서 가져오는것
    * */
    @GetMapping("/myInfo")
    @LoginCheck
    public UserDto getUserInfoById(@ModelAttribute UserDto userDto) {

        String id = userDto.getId();

        UserDto userInfo = userService.getUserInfoById(id);

        return userInfo;
//        return userDto;
    }
}