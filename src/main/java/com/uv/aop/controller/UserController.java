package com.uv.aop.controller;

import com.uv.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author liuwei
 * @date 2018/12/10 18:41
 *
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getname")
    public String getName(String id) {
        return userService.getName(id);
    }

}
