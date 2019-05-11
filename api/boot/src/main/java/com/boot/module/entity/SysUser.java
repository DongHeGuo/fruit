package com.boot.module.entity;

import lombok.Data;

/**
 * 系统用户实体类
 * @author ziv
 * @date 2019-05-07
 */
@Data
public class SysUser {

    private Integer userId;

    private String userName;

    private String password;
}
