package io.github.jaywong1024.enums;

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
    USER("user:"),
    ROLE("role:"),
    PERMISSION("permission:"),
    PERMISSION_LINK("permission_link:");

    private final String code;

}