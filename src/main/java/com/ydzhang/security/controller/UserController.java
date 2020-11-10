package com.ydzhang.security.controller;

import com.ydzhang.security.config.JsonResult;
import com.ydzhang.security.config.ResultTool;
import com.ydzhang.security.entity.SysUser;
import com.ydzhang.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService sysUserService;

    @GetMapping("/getUser")
    public JsonResult getUser() {
        SysUser users = sysUserService.queryUserInfo("user1");
        return ResultTool.success(users);
    }
    @GetMapping("/test")
    public JsonResult test() {
        return ResultTool.success("hello world");
    }

}
