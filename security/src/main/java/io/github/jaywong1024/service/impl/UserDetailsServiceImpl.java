package io.github.jaywong1024.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.github.jaywong1024.entity.PermissionInfo;
import io.github.jaywong1024.entity.RoleInfo;
import io.github.jaywong1024.entity.UserDetail;
import io.github.jaywong1024.entity.UserInfo;
import io.github.jaywong1024.service.PermissionInfoService;
import io.github.jaywong1024.service.RoleInfoService;
import io.github.jaywong1024.service.UserInfoService;
import io.github.jaywong1024.utils.BusinessExceptionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 实现 UserDetailsService 读取用户信息
 * @author 黄汉杰
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private RoleInfoService roleInfoService;
    @Resource
    private PermissionInfoService permissionInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load user by username: {}", username);
        // 1.从数据库获取 user
        UserInfo userInfo = this.userInfoService.getByUsername(username);
        // 2.校验用户是否存在
        BusinessExceptionUtils.assertTrue(() -> Objects.nonNull(userInfo), "userDoesNotExist");
        // 3.获取用户角色
        List<RoleInfo> roles = this.roleInfoService.getByUserId(userInfo.getId());
        // 3.获取用户权限
        Set<PermissionInfo> permissions = this.permissionInfoService
                .getByRoleIds(roles.stream().map(RoleInfo::getId).collect(Collectors.toList()));
        // 4.构建 UserDetails 对象
        return UserDetail.builder()
                .userInfo(userInfo)
                .roles(roles)
                .permissions(permissions)
                .build();
    }

}