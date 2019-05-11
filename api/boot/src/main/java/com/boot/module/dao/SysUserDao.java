package com.boot.module.dao;

import cn.hutool.json.JSONObject;
import com.boot.module.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数据持久层接口
 * @author ziv
 * @date 2019-05-07
 */
@Mapper
public interface SysUserDao {

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return SysUser
     */
    SysUser loadUserByUsername(String userName);

    void testSave(@Param("tableName")String tableName, @Param("object") JSONObject object);
}
