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

    USER("user:");

    private final String code;

}