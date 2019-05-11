package com.boot.module.controller;

import com.boot.common.result.Result;
import com.boot.module.entity.SysPlatform;
import com.boot.module.service.SysPlatformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 平台设置控制类
 * @author ziv
 * @date 2019-05-07
 */
@Slf4j
@RestController
@Api(tags = {"平台信息接口"})
@RequestMapping(value = "sys/platform")
public class PlatformController {

    @Resource
    private SysPlatformService platformService;

    @GetMapping
    @ApiOperation(value = "获取平台信息")
    @ApiImplicitParam(name = "platformType", value = "平台类型:1-水果", paramType = "int", required = true)
    public Result getInfo(Integer platformType) {
        Result result;
        try {
            SysPlatform platform = platformService.getInfo(platformType);
            result = Result.success(platform);
        } catch (Exception e) {
            log.error("获取平台信息异常");
            log.error(e.getMessage());
            result = Result.createByErrorMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping
    @ApiOperation(value = "保存平台信息", notes = "有主键时更新，无主键时保存")
    public Result editPlatform(SysPlatform platform) {
        Result result;
        try {
            platformService.savePlatform(platform);
            result = Result.success(platform);
        } catch (Exception e) {
            log.error("保存平台信息异常");
            log.error(e.getMessage());
            result = Result.createByErrorMessage(e.getMessage());
        }
        return result;
    }
}
