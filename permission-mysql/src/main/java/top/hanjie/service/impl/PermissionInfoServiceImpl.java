package top.hanjie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import top.hanjie.entity.PermissionInfo;
import top.hanjie.entity.RolePermissionLink;
import top.hanjie.enums.CacheGroup;
import top.hanjie.enums.StatusEnum;
import top.hanjie.mapper.PermissionInfoMapper;
import top.hanjie.service.PermissionInfoService;
import top.hanjie.utils.CacheUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
     *
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

    @Override
    public List<PermissionInfo> getByRoleIds(List<String> ids) {
        List<PermissionInfo> result = new ArrayList<>();
        ids.forEach(roleId -> {
            List<RolePermissionLink> links = BeanUtil.copyToList(CacheUtils.get(CacheGroup.PERMISSION_LINK, "all", List.class), RolePermissionLink.class);
            links.stream().filter(f -> Objects.equals(f.getStatus(), StatusEnum.NORMAL.getCode())
                    && ids.contains(f.getRoleId())).collect(Collectors.toList()).forEach(link -> {
                PermissionInfo permissionInfo = CacheUtils.get(CacheGroup.PERMISSION, link.getPermissionId(), PermissionInfo.class);
                if (Objects.nonNull(permissionInfo) && Objects.equals(StatusEnum.NORMAL.getCode(), permissionInfo.getStatus())) {
                    result.add(permissionInfo);
                }
            });
        });
        return result;
    }

}