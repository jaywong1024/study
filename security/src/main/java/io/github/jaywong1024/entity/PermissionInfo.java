package io.github.jaywong1024.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 权限信息
 *
 * @author 黄汉杰
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PermissionInfo extends BaseInfo {

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限 url
     */
    private String url;
    /**
     * 方法类型
     */
    private String method;

}