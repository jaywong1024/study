package io.github.jaywong1024.filter;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.github.jaywong1024.entity.UserDetail;
import io.github.jaywong1024.enums.CacheGroup;
import io.github.jaywong1024.utils.CacheUtils;
import io.github.jaywong1024.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * jwt 权限过滤器
 *
 * @author 黄汉杰
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // 1.获取 token
        String token = JwtUtils.getToken(request);
        // 2.校验 token 是否存在 && 判断 token 是否过期
        if (StrUtil.isNotBlank(token) && !JwtUtils.expired(token)) {
            // 3.获取 username
            String username = JwtUtils.getSubject(token);
            // 4.在缓存中获取用户信息
            UserDetail user = CacheUtils.get(CacheGroup.USER, username, UserDetail.class);
            // 5.插入 authentication 到上下文中
            if (Objects.nonNull(user)) {
                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}