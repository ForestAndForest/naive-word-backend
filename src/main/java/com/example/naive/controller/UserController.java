package com.example.naive.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.naive.annotation.MyResponseBody;
import com.example.naive.domain.User;
import com.example.naive.domain.vo.UserUpdate;
import com.example.naive.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 29002
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
@MyResponseBody
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/signup")
    public Object signup(@RequestBody User user) {
        return userService.signUp(user);
    }

    @GetMapping("/isLogin")
    public Object isLogin(@RequestParam String uid) {
        return StpUtil.isLogin(uid);
    }

    @GetMapping("/getUserByUid")
    public Object getUserByUid(@RequestParam String uid) {
        return userService.getUserByUid(uid);
    }

    @PostMapping("/getUpdateUsers")
    public Object getUpdateUsers(@RequestBody List<UserUpdate> userUpdates) {
        return userService.getUpdateUsers(userUpdates);
    }

    @PostMapping("/updateUser")
    public Object updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/logout")
    public Object loginOut() {
        return userService.logout();
    }


}
