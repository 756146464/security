package com.ydzhang.security.mapper;

import com.ydzhang.security.entity.SysUser;

public interface UserMapper {

    //根据账号查询用户
    SysUser queryUserInfo(String userName);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUser sysUser);
}
