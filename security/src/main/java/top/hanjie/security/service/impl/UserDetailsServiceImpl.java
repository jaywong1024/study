package top.hanjie.security.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.hanjie.security.entity.UserInfo;
import top.hanjie.security.service.PermissionInfoService;
import top.hanjie.security.service.UserInfoService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
        // 3.获取用户权限
        List<SimpleGrantedAuthority> roles = permissionInfoService.getByUserId(userInfo.getId());
        // 4.构建 UserDetail 对象
        return new User(userInfo.getUsername(), userInfo.getPassword(), roles);
    }

}