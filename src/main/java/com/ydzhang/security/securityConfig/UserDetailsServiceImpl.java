package com.ydzhang.security.securityConfig;

import com.ydzhang.security.entity.SysPermission;
import com.ydzhang.security.entity.SysUser;
import com.ydzhang.security.mapper.SysPermissionMapper;
import com.ydzhang.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserMapper userMapper;
    @Autowired
    private void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private void setSysPermissionMapper(SysPermissionMapper sysPermissionMapper){
        this.sysPermissionMapper = sysPermissionMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (userName == null || "".equals(userName)){
            new RuntimeException("用户不能为空");
        }
        SysUser sysUser = userMapper.queryUserInfo(userName);
        if (sysUser == null){
            new RuntimeException("用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<SysPermission> sysPermissions = sysPermissionMapper.selectListByUser(sysUser.getId());
        sysPermissions.forEach( v -> {
            GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(v.getPermissionCode());
            grantedAuthorities.add(simpleGrantedAuthority);
        });
        return new User(sysUser.getAccount(),sysUser.getPassword(),sysUser.getEnabled(),sysUser.getAccountNonExpired(),sysUser.getCredentialsNonExpired(),sysUser.getAccountNonLocked(),grantedAuthorities);
    }
}
