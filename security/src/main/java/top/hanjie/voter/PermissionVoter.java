package top.hanjie.voter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import top.hanjie.entity.PermissionInfo;
import top.hanjie.entity.UserDetail;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限投票
 *
 * @author 黄汉杰
 */
@Slf4j
public class PermissionVoter implements AccessDecisionVoter<FilterInvocation> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return Boolean.TRUE;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Boolean.TRUE;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        // 1.校验非空
        assert Objects.nonNull(authentication) && Objects.nonNull(object);
        // 2.如果是匿名用户则直接弃权（将投票权交给 WebExpressionVoter）
        if (authentication instanceof AnonymousAuthenticationToken) {
            return ACCESS_ABSTAIN;
        }
        // 3.否则校验是否有接口调用权限
        return this.permission(authentication, object);
    }

    private int permission(Authentication authentication, FilterInvocation object) {
        // 1.拿到请求的 url 和 method
        String url = object.getRequestUrl();
        String method = object.getRequest().getMethod();
        // 2.获取当前用户权限
        Set<PermissionInfo> permissions = ((UserDetail) authentication.getPrincipal()).getPermissions();
        // 3.判断是否有权限访问
        List<PermissionInfo> authorities = permissions.stream()
                .filter(p -> Objects.equals(p.getUrl(), url) && Objects.equals(p.getMethod(), method))
                .collect(Collectors.toList());
        // 4.有权限投赞成票；否则反对票
        return authorities.size() > 0 ? ACCESS_GRANTED : ACCESS_DENIED;
    }

}