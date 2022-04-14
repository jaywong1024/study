package top.hanjie.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import top.hanjie.security.cache.Cache;
import top.hanjie.security.cache.CacheGroup;
import top.hanjie.security.dto.LoginDto;
import top.hanjie.security.jwt.JwtUtils;
import top.hanjie.security.service.SecurityService;

import javax.annotation.Resource;

/**
 * 权限相关接口实现类.
 *
 * @author 黄汉杰
 * <p>描述：权限相关接口实现类<p>
 * <p>文件名称: SecurityServiceImpl</p>
 * <p>创建时间：2022/4/14 0014 13:59<p>
 * Copyright (C), 2018-2022, 龙腾出行
 * @since 1.0.0
 * History:
 */
@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public LoginDto.Out login(LoginDto.In in) {
        log.info("User login, username: {}", in.getUsername());
        // 1.构建 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(in.getUsername(), in.getPassword());
        // 2.认证
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        // 3.保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 4.拿到用户信息
        User user = (User) authentication.getPrincipal();
        // 5.根据 username 生成自定义 Token
        String token = jwtUtils.createToken(user.getUsername());
        // 6.写入缓存
        Cache.set(CacheGroup.USER, user.getUsername(), user, 3600L * 24);
        // 7.组装返回
        return LoginDto.Out.builder().token(token).build();
    }

}