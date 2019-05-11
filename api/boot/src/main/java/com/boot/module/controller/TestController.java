package com.boot.module.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.boot.common.result.Result;
import com.boot.module.entity.SysUser;
import com.boot.module.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @Resource
    private UserService userService;

    @PostMapping("saveData")
    public Result saveData(String jsonStr) {
        JSONObject object = JSONUtil.parseObj(jsonStr);
        userService.testSave(object);
        return Result.success();
    }

    public static void main(String[] args) {
        String password = "123456";
        System.out.println(new BCryptPasswordEncoder().encode(password));
    }
}
