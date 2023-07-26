package io.github.jaywong1024.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import io.github.jaywong1024.entity.RolePermissionLink;
import io.github.jaywong1024.enums.CacheGroup;
import io.github.jaywong1024.mapper.RolePermissionLinkMapper;
import io.github.jaywong1024.service.RolePermissionLinkService;
import io.github.jaywong1024.utils.CacheUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 角色与权限关联接口实现
 *
 * @author 黄汉杰
 */
@Service
@DependsOn("cacheUtils")
public class RolePermissionLinkServiceImpl
        extends ServiceImpl<RolePermissionLinkMapper, RolePermissionLink>
        implements RolePermissionLinkService {

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
            List<RolePermissionLink> all = this.list();
            CacheUtils.set(CacheGroup.PERMISSION_LINK, "all", all, 3600 * 24 * 100);
            all.forEach(r -> CacheUtils.set(CacheGroup.PERMISSION_LINK, r.getId(), r, 3600 * 24 * 100));
        } catch (Exception e) {
            log.error("RolePermissionLink insert cache error!");
        }
    }

}