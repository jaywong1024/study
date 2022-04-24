package top.hanjie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import top.hanjie.entity.PermissionInfo;
import top.hanjie.enums.CacheGroup;
import top.hanjie.mapper.PermissionInfoMapper;
import top.hanjie.service.PermissionInfoService;
import top.hanjie.utils.CacheUtils;

import javax.annotation.PostConstruct;

/**
 * 权限接口实现
 *
 * @author 黄汉杰
 */
@Service
@DependsOn("cacheUtils")
public class PermissionInfoServiceImpl
        extends ServiceImpl<PermissionInfoMapper, PermissionInfo>
        implements PermissionInfoService {

    @PostConstruct
    public void init() {
        // 将所有数据写到缓存中
        this.cache();
    }

    /**
     * 将所有数据写到缓存中
     * @author 黄汉杰
     * @date 2022/4/24 0024 15:09
     */
    public void cache() {
        try {
            this.list().forEach(p -> CacheUtils.set(CacheGroup.PERMISSION, p.getId(), p, 3600 * 24 * 100));
        } catch (Exception e) {
            log.error("Permission insert cache error!");
        }
    }

}