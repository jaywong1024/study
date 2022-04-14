package top.hanjie.security.jwt;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * jwt 工具类
 *
 * @author 黄汉杰
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtProperties jwtProperties;

    /**
     * 创建令牌
     *
     * @param subject 主题
     * @author 黄汉杰
     * @date 2022/4/13 0013 16:19
     */
    public String createToken(String subject) {
        // 1.当前时间
        final Date now = new Date();
        // 2.过期时间
        final Date expirationDate = new Date(now.getTime() + jwtProperties.getExpirationTime() * 1000);
        // 3.生成令牌
        return jwtProperties.getPrefix() + Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();
    }

    /**
     * 判断 token 格式是否合法
     *
     * @param token 令牌
     * @return boolean
     * @author 黄汉杰
     * @date 2022/4/14 0014 11:57
     */
    public boolean verifyToken(String token) {
        return StrUtil.isNotBlank(token) && token.startsWith(jwtProperties.getPrefix());
    }

    /**
     * 从 request 中获取 token
     *
     * @param request 请求
     * @author 黄汉杰
     * @date 2022/4/14 0014 13:42
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        return this.verifyToken(token) ? token.substring(jwtProperties.getPrefix().length()) : StrUtil.EMPTY;
    }

    /**
     * 从 token 获取主题
     *
     * @param token 令牌
     * @author 黄汉杰
     * @date 2022/4/14 0014 13:49
     */
    public String getSubject(String token) {
        Claims claims = this.getClaims(token);
        return Objects.nonNull(claims) ? claims.getSubject() : StrUtil.EMPTY;
    }

    /**
     * 根据 token 获取信息
     *
     * @param token 令牌
     * @author 黄汉杰
     * @date 2022/4/14 0014 13:45
     */
    private Claims getClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.warn("Jwt parser, token: {}, error: {}", token, e.getMessage());
        }
        return claims;
    }

}