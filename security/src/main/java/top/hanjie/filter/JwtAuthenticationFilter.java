package top.hanjie.filter;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import top.hanjie.utils.BusinessExceptionUtils;
import top.hanjie.entity.UserDetail;
import top.hanjie.enums.CacheGroup;
import top.hanjie.utils.CacheUtils;
import top.hanjie.utils.JwtUtils;

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
        // 2.校验 token 是否存在
        if (StrUtil.isNotBlank(token)) {
            // 3.判断 token 是否过期
            BusinessExceptionUtils.assertFalse(() -> JwtUtils.expired(token), "loginExpiration");
            // 4.获取 username
            String username = JwtUtils.getSubject(token);
            // 5.在缓存中获取用户信息
            UserDetail user = CacheUtils.get(CacheGroup.USER, username, UserDetail.class);
            // 6.插入 authentication 到上下文中
            if (Objects.nonNull(user)) {
                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}