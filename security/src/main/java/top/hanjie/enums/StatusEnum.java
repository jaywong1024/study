package top.hanjie.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态枚举
 *
 * @author 黄汉杰
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    /**
     * 状态（0正常；1删除）
     */
    NORMAL(0),
    DELETE(1);

    private final Integer code;

}