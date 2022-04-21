package top.hanjie.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色信息
 *
 * @author 黄汉杰
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RoleInfo extends BaseInfo {

    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色名称
     */
    private String code;
    /**
     * 角色名称
     */
    private String remark;

}