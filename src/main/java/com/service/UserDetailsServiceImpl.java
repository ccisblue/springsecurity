package com.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;

public class UserDetailsServiceImpl implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 以下可以替换成用其他方式获取用户信息
        if(username.equals("admin")) {
            Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
            SwitchUserGrantedAuthority authority = new SwitchUserGrantedAuthority("ROLE_USER", null); 
            auths.add(authority);
            User user = new User(username, "123456", false, false, false, false, auths);
            return user;
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }

}
