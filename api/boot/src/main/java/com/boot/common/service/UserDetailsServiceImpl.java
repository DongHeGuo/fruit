package com.boot.common.service;

import com.boot.common.beans.MyUserDetails;
import com.boot.module.dao.SysUserDao;
import com.boot.module.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务逻辑实现接口
 * @author ziv
 * @date 2019-03-06
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 从数据库查询用户
        log.info("查询用户");
        SysUser user = userDao.loadUserByUsername(userName);
        // TODO:获取用户权限
        List permissionList = new ArrayList();
        permissionList.add("sys:redis:read");

        MyUserDetails userDetails = new MyUserDetails(user, permissionList);
        return userDetails;
    }
}
