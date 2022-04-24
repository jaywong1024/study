package top.hanjie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import top.hanjie.entity.RoleInfo;
import top.hanjie.enums.CacheGroup;
import top.hanjie.mapper.RoleInfoMapper;
import top.hanjie.service.RoleInfoService;
import top.hanjie.utils.CacheUtils;

import javax.annotation.PostConstruct;

/**
 * 角色接口实现
 *
 * @author 黄汉杰
 */
@Service
@DependsOn("cacheUtils")
public class RoleInfoServiceImpl
        extends ServiceImpl<RoleInfoMapper, RoleInfo>
        implements RoleInfoService {


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
            this.list().forEach(r -> CacheUtils.set(CacheGroup.ROLE, r.getId(), r, 3600 * 24 * 100));
        } catch (Exception e) {
            log.error("Role insert cache error!");
        }
    }

}