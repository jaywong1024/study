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
     * 数据库名
     */
    private String databaseName;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 条件
     */
    private String conditionExpression;
    /**
     * 备注
     */
    private String remark;

}