package top.hanjie.security.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import top.hanjie.common.utils.SpringContextUtils;
import top.hanjie.security.enums.CacheGroup;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * 缓存
 *
 * @author 黄汉杰
 */
@Slf4j
@Component
public class CacheUtils  {

    private static RedissonClient REDISSON_CLIENT;

    @PostConstruct
    public void init() {
        REDISSON_CLIENT = SpringContextUtils.getBean(RedissonClient.class);
    }

    /**
     * 写入缓存
     *
     * @param group 分组
     * @param key   键
     * @param val   值
     * @param time  过期时间（秒）
     * @author 黄汉杰
     * @date 2022/4/14 0014 15:12
     */
    public static void set(CacheGroup group, String key, Object val, long time) {
        REDISSON_CLIENT.getBucket(group + key).set(val, time, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存
     *
     * @param group 分组
     * @param key   键
     * @author 黄汉杰
     * @date 2022/4/14 0014 15:42
     */
    public static <T> T get(CacheGroup group, String key, Class<? extends T> clazz) {
        RBucket<T> bucket = REDISSON_CLIENT.getBucket(group + key);
        return bucket.get();
    }

}