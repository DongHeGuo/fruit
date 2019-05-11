package com.boot.common.controller;

import cn.hutool.core.date.DateUtil;
import com.boot.common.result.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * 文件上传控制类
 * @author ziv
 * @date 2019-05-09
 */
@Slf4j
@RestController
@RequestMapping(value = "fileUpload")
public class FileUploadController {

    @Value("${fileSavePath}")
    private String fileSavePath;

    @GetMapping(value = "test")
    public String test() {
        return fileSavePath;
    }

    @PostMapping(value = "singleImg")
    @ApiOperation(value = "上传单张图片")
    @ApiImplicitParam(name = "file", value = "文件", paramType = "file", required = true)
    public Result singleImg(MultipartFile file) {
        Result result;
        try {
            // 日期文件夹
            String dataPackage = DateUtil.format(new Date(), "yyyy-MM-dd");
            // 获取文件名称
            String fileOriginName = file.getOriginalFilename();
            // 服务器文件存储路径
            String savePath = fileSavePath + "/img/" + dataPackage + "/" + System.currentTimeMillis() + "/" + fileOriginName;
            File destFile = new File(savePath);
            // 创建目录
            destFile.getParentFile().mkdirs();
            // 保存文件
            file.transferTo(destFile);
            result = Result.success(savePath);

        } catch (Exception e) {
            log.error("图片上传失败");
            result = Result.createByErrorMessage(e.getMessage());
        }
        return result;
    }
}
