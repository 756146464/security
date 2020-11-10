package com.ydzhang.security.service;

import com.ydzhang.security.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {

    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(com.ydzhang.security.entity.SysPermission record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(com.ydzhang.security.entity.SysPermission record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    com.ydzhang.security.entity.SysPermission selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(com.ydzhang.security.entity.SysPermission record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(com.ydzhang.security.entity.SysPermission record);


    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    List<com.ydzhang.security.entity.SysPermission> selectListByUser(Integer id);

    /**
     * 查询具体某个接口的权限
     *
     * @param path 接口路径
     * @return
     */
    List<SysPermission> selectListByPath(String path);
}
