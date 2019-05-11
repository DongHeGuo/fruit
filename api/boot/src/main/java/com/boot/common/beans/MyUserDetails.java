package com.boot.common.beans;

import com.boot.module.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息类
 * @author ziv
 * @date 2019-03-06
 */
public class MyUserDetails implements UserDetails {

    private SysUser user;

    private List<String> permissionList;

    public MyUserDetails(SysUser user, List<String> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionList
                .stream()
                // 过滤规则
                .filter(permission -> permission != null)
                .map(permission -> new SimpleGrantedAuthority(permission))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getUserId() {
        return this.user.getUserId();
    }
}
