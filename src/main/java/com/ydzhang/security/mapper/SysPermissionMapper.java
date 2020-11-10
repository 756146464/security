package com.ydzhang.security.mapper;

import com.ydzhang.security.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
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
    int insert(SysPermission record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(SysPermission record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    SysPermission selectByPrimaryKey(Integer id);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    List<SysPermission> selectListByUser(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SysPermission record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SysPermission record);

    /**
     * 查询具体某个接口的权限
     *
     * @param path 接口路径
     * @return
     */
    List<SysPermission> selectListByPath(String path);
}