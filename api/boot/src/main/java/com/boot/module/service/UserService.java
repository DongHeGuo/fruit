package com.boot.module.service;

import cn.hutool.json.JSONObject;
import com.boot.module.dao.SysUserDao;
import com.boot.module.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户业务逻辑类
 * @author ziv
 * @date 2019-05-07
 */
@Service
public class UserService {

    @Resource
    private SysUserDao userDao;

    public void testSave(JSONObject object) {
        String tableName = "test_bbbs_11";
        userDao.testSave(tableName, object);
    }
}
