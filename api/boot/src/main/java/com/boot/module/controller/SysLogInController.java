package com.boot.module.controller;

import com.boot.common.result.Result;
import com.boot.module.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统登录控制类
 * @author ziv
 * @date 2019-05-07
 */
@Api(tags = {"系统登录接口"})
@Slf4j
@RestController
@RequestMapping(value = "sys")
public class SysLogInController {

    @Resource
    private LoginService loginService;

    /**
     * 系统登录
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @ApiOperation(value = "系统登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "string", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "string", required = true)
    })
    @GetMapping(value = "login")
    public Result login(String userName, String password) {
        Result result;
        try {
            String token = loginService.login(userName, password);
            result = Result.success(token);
        } catch (Exception e) {
            log.error("登录异常！");
            log.error(e.getMessage());
            result = Result.createByErrorMessage(e.getMessage());
        }
        return result;
    }
}
