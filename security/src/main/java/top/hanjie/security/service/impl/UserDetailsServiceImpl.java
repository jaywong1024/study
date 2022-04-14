package top.hanjie.security.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 实现 UserDetailsService 读取用户信息
 * @author 黄汉杰
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load user by username: {}", username);
        // 1.校验用户是否存在
        if (!Objects.equals("huanghj", username)) {
            throw new UsernameNotFoundException("User name does not exist, login failed.");
        }
        // 2.构建 UserDetail 对象
        return new User("huanghj", "$2a$10$iMsf1kHXZOUTqpCtO9UJeOWgBtiZLTcZyARdNz5duiipO5eRti9Te", new ArrayList<>());
    }

}