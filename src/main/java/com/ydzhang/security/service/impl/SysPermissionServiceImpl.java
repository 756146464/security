package com.ydzhang.security.service.impl;

import com.ydzhang.security.entity.SysPermission;
import com.ydzhang.security.mapper.SysPermissionMapper;
import com.ydzhang.security.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private void setSysPermissionMapper(SysPermissionMapper sysPermissionMapper){
        this.sysPermissionMapper = sysPermissionMapper;
    }


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(com.ydzhang.security.entity.SysPermission record) {
        return 0;
    }

    @Override
    public int insertSelective(com.ydzhang.security.entity.SysPermission record) {
        return 0;
    }

    @Override
    public com.ydzhang.security.entity.SysPermission selectByPrimaryKey(Integer id) {
        return  null;
    }

    @Override
    public int updateByPrimaryKeySelective(com.ydzhang.security.entity.SysPermission record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(com.ydzhang.security.entity.SysPermission record) {
        return 0;
    }

    @Override
    public List<com.ydzhang.security.entity.SysPermission> selectListByUser(Integer id) {
        return sysPermissionMapper.selectListByUser(id);
    }

    @Override
    public List<SysPermission> selectListByPath(String path) {
        return sysPermissionMapper.selectListByPath(path);
    }
}
