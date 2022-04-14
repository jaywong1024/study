package top.hanjie.security.jwt;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt 权限过滤器
 * @author 黄汉杰
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // 1.获取 token
        String token = jwtUtils.getToken(request);
        // 2.获取 username
        String username = jwtUtils.getSubject(token);
        // 3.在缓存中获取用户信息

        filterChain.doFilter(request, response);
    }

}