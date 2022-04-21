package top.hanjie.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户与角色关联表
 *
 * @author 黄汉杰
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserRoleLink extends BaseInfo {

    /**
     * 角色表主键
     */
    private String roleId;
    /**
     * 用户表主键
     */
    private String userId;

}