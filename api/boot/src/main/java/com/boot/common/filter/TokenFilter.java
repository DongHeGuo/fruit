package com.boot.common.filter;

import com.boot.common.beans.TokenInfo;
import com.boot.common.utils.JwtTokenUtil;
import com.boot.common.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token拦截器
 * @author ziv
 * @date 2019-04-17
 */
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Resource
    private JwtTokenUtil tokenUtil;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            // 从redis获取token
            TokenInfo tokenInfo = redisUtils.get(token, TokenInfo.class);
            // 验证token
            if (!StringUtils.isEmpty(tokenInfo) && token.equals(tokenInfo.getToken())) {
                try {
                    // 从token获取用户名
                    String userName = tokenUtil.getUserNameFromToken(token);
                    // 获取用户信息
                    if (!StringUtils.isEmpty(userName)) {
                        // 登录
                        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        redisUtils.setExpire(token);
                    }
                } catch (Exception e) {
                    log.error("登录失败！");
                    log.error(e.getMessage());
                }
            }
        }
        chain.doFilter(request, response);
    }
}
