package top.hanjie.security.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.hanjie.security.entity.PermissionInfo;
import top.hanjie.security.entity.RoleInfo;
import top.hanjie.security.entity.UserDetail;
import top.hanjie.security.entity.UserInfo;
import top.hanjie.security.service.PermissionInfoService;
import top.hanjie.security.service.RoleInfoService;
import top.hanjie.security.service.UserInfoService;

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
    private RoleInfoService  roleInfoService;
    @Resource
    private PermissionInfoService permissionInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load user by username: {}", username);
        // 1.从数据库获取 user
        UserInfo userInfo = this.userInfoService.getByUsername(username);
        // 2.校验用户是否存在
        if (Objects.isNull(userInfo)) {
            throw new UsernameNotFoundException("User name does not exist, login failed.");
        }
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