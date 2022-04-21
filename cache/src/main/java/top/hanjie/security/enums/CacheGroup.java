package top.hanjie.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 缓存分组
 * @author 黄汉杰
 */
@Getter
@AllArgsConstructor
public enum CacheGroup {

    /**
     * 用户：user
     */
    USER("user:");

    private final String code;

}