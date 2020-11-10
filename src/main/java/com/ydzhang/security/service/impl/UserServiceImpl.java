package com.ydzhang.security.service.impl;

import com.ydzhang.security.entity.SysUser;
import com.ydzhang.security.mapper.UserMapper;
import com.ydzhang.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    @Autowired
    private void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public SysUser queryUserInfo(String userName) {
        return userMapper.queryUserInfo(userName);
    }

    @Override
    public int update(SysUser sysUser) {
        return userMapper.update(sysUser);
    }
}
