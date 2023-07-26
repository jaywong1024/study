package io.github.jaywong1024.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色与权限关联表
 *
 * @author 黄汉杰
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RolePermissionLink extends BaseInfo {

    /**
     * 角色表主键
     */
    private String roleId;
    /**
     * 权限表主键
     */
    private String permissionId;

}