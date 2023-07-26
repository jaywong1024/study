package io.github.jaywong1024.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import io.github.jaywong1024.entity.PermissionInfo;
import io.github.jaywong1024.entity.RolePermissionLink;
import io.github.jaywong1024.enums.StatusEnum;
import io.github.jaywong1024.mapper.PermissionInfoMapper;
import io.github.jaywong1024.service.PermissionInfoService;
import io.github.jaywong1024.service.RolePermissionLinkService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限接口实现
 *
 * @author 黄汉杰
 */
@Service
public class PermissionInfoServiceImpl
        extends ServiceImpl<PermissionInfoMapper, PermissionInfo>
        implements PermissionInfoService {

    @Resource
    private RolePermissionLinkService rolePermissionLinkService;


    @Override
    public Set<PermissionInfo> getByRoleIds(List<String> roleIds) {
        // 1.定义结果集
        Set<PermissionInfo> permissions = new HashSet<>();
        // 2.获取权限列表
        List<RolePermissionLink> permissionLink = this.rolePermissionLinkService
                .list(Wrappers.<RolePermissionLink>lambdaQuery()
                        .in(RolePermissionLink::getRoleId, roleIds)
                        .eq(RolePermissionLink::getStatus, StatusEnum.NORMAL.getCode())
                );
        if (CollectionUtil.isNotEmpty(permissionLink)) {
            permissions = new HashSet<>(this.list(Wrappers.<PermissionInfo>lambdaQuery()
                    .in(PermissionInfo::getId, permissionLink.stream()
                            .map(RolePermissionLink::getPermissionId).collect(Collectors.toSet()))
                    .eq(PermissionInfo::getStatus, StatusEnum.NORMAL.getCode())
            ));
        }
        return permissions;
    }

}