package top.hanjie.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 测试.
 *
 * @author 黄汉杰
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Test extends BaseInfo {

    /**
     * 备注
     */
    private String remark;

}