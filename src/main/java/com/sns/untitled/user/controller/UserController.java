package com.sns.untitled.user.controller;

import com.sns.untitled.user.dto.UserDto;
import com.sns.untitled.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public void login(@RequestParam String id, @RequestParam String password, HttpSession httpSession) {
        UserDto user = userService.login(id, password);
        httpSession.setAttribute("userInfo", user);
    }

    @PostMapping("/logout")
    public void logout() {
    }

    @GetMapping("/{id}")
    public UserDto getUserInfo(@PathVariable String id) {
        UserDto user = userService.getUserInfo(id);
        return user;
    }
}
