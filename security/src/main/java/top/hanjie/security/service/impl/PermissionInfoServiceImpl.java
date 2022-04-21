package top.hanjie.security.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hanjie.security.entity.PermissionInfo;
import top.hanjie.security.entity.RolePermissionLink;
import top.hanjie.security.mapper.PermissionInfoMapper;
import top.hanjie.security.service.PermissionInfoService;
import top.hanjie.security.service.RolePermissionLinkService;

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
                .list(Wrappers.<RolePermissionLink>lambdaQuery().in(RolePermissionLink::getRoleId, roleIds));
        if (CollectionUtil.isNotEmpty(permissionLink)) {
            permissions = new HashSet<>(
                    this.list(Wrappers.<PermissionInfo>lambdaQuery().in(
                            PermissionInfo::getId,
                            permissionLink.stream().map(RolePermissionLink::getPermissionId).collect(Collectors.toSet())
                    )));
        }
        return permissions;
    }

}