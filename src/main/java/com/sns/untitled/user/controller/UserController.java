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
    public String login(@RequestParam String id, @RequestParam String password, HttpSession httpSession) {
        UserDto userInfo = userService.login(id, password);

        if(userInfo != null) {
//            httpSession.setAttribute("userInfo", userInfo);
            httpSession.setAttribute("name", "seyoung");
        } else {

        }
        return httpSession.getId();
    }

    @PostMapping("/logout")
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }

    @GetMapping("/{id}")
    public UserDto getUserInfo(@PathVariable String id) {
        UserDto userInfo = userService.getUserInfo(id);
        return userInfo;
    }
}