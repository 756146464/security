package com.ydzhang.security.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 权限表
 */
@ApiModel(value = "com-ydzhang-security-entity-SysPermission")
@Data
public class SysPermission {
    private static final long serialVersionUID = -71969734644822184L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /**
     * 权限code
     */
    @ApiModelProperty(value = "权限code")
    private String permissionCode;

    /**
     * 权限名
     */
    @ApiModelProperty(value = "权限名")
    private String permissionName;
}