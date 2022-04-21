package top.hanjie.security.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import top.hanjie.security.entity.PermissionInfo;
import top.hanjie.security.entity.RolePermissionLink;
import top.hanjie.security.entity.UserRoleLink;
import top.hanjie.security.mapper.PermissionInfoMapper;
import top.hanjie.security.service.PermissionInfoService;
import top.hanjie.security.service.RolePermissionLinkService;
import top.hanjie.security.service.UserRoleLinkService;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private UserRoleLinkService userRoleLinkService;
    @Resource
    private RolePermissionLinkService rolePermissionLinkService;

    @Override
    public List<SimpleGrantedAuthority> getByUserId(String userId) {
        // 1.定义结果集
        List<SimpleGrantedAuthority> result = new ArrayList<>();
        // 2.获取用户角色列表
        List<UserRoleLink> roleLink = this.userRoleLinkService
                .list(Wrappers.<UserRoleLink>lambdaQuery().eq(UserRoleLink::getUserId, userId));
        // 3.获取权限列表
        if (CollectionUtil.isNotEmpty(roleLink)) {
            List<RolePermissionLink> permissionLink = this.rolePermissionLinkService
                    .list(Wrappers.<RolePermissionLink>lambdaQuery().in(
                            RolePermissionLink::getRoleId,
                            roleLink.stream().map(UserRoleLink::getRoleId).collect(Collectors.toSet())
                    ));
            if (CollectionUtil.isNotEmpty(permissionLink)) {
                Set<PermissionInfo> permissions = new HashSet<>(
                        this.list(Wrappers.<PermissionInfo>lambdaQuery().in(
                                PermissionInfo::getId,
                                permissionLink.stream().map(RolePermissionLink::getPermissionId).collect(Collectors.toSet())
                        )));
                permissions.forEach(p -> result.add(new SimpleGrantedAuthority(JSONObject.toJSONString(p))));
            }
        }
        return result;
    }

}