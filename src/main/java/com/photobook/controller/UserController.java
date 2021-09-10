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
    
    // 중복로그인 검증필요, 현재 session에 값이 있는지?
    @PostMapping("/login")
    public void login(@RequestParam @NotBlank String id, @RequestParam @NotBlank String password) {

        UserDto userInfo = userService.getUserInfoByIdAndPassword(id, password);

        loginService.setLoginUserInfo(userInfo);
    }

    @PostMapping("/logout")
    @LoginCheck
    public void logout() {
        System.out.println("로그아웃");
        loginService.removeLoginUserInfo();
    }

    // 현재 session에 있는 id와 동일한지 검증필요    
//    @GetMapping("/{id}")
//    @LoginCheck
//    public UserDto getUserInfoById(@PathVariable @NotBlank String id) {
////        String id = loginService.getLoginUserInfo().getId();
//        UserDto userInfo = userService.getUserInfoById(id);
//
//        return userInfo;
//    }

    // session에 접근하여 회원정보조회, @LoginCheck에서도 중복접근하는 문제(advice에서 return 값 받아올수없는지?)
    // session에 회원정보를 모두 가지고 있는것 vs 필요에 따라 그때그때 쿼리로 조회해 오는것
//    @GetMapping("/myInfo")
//    @LoginCheck
//    public UserDto getUserInfoById() {
//        UserDto userInfo = loginService.getLoginUserInfo();
//        return userInfo;
//    }

    @GetMapping("/myInfo")
    @LoginCheck
    public UserDto getUserInfoById(@ModelAttribute UserDto userDto) {
        System.out.println("컨트롤러" + "");
        String id = "";
        System.out.println(userDto.toString());
        UserDto userInfo = new UserDto();
//        = userService.getUserInfoById(id);

        // my-idea
        return userDto;
    }
}