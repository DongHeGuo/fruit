package com.boot.module.service;

import com.boot.common.beans.MyUserDetails;
import com.boot.common.beans.TokenInfo;
import com.boot.common.utils.JwtTokenUtil;
import com.boot.common.utils.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 用户业务逻辑实现类
 * @author ziv
 * @date 2019-03-12
 */
@Service
public class LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisUtils redisUtils;

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码
     * @return String
     */
    public String login(String userName, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        MyUserDetails userDetails = (MyUserDetails)userDetailsService.loadUserByUsername(userName);
        // TODO： 获取旧token，并从redis中删除
        String token = jwtTokenUtil.generateToken(userDetails);
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(userDetails.getUserId());
        tokenInfo.setUserName(userDetails.getUsername());
        tokenInfo.setToken(token);
        // 保存到redis
        redisUtils.set(token, tokenInfo);
        return token;
    }
}
