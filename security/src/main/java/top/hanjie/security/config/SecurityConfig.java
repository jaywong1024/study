package top.hanjie.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.hanjie.security.filter.JwtAuthenticationFilter;
import top.hanjie.security.voter.PermissionVoter;

import java.util.Arrays;
import java.util.List;

/**
 * Security 配置类
 * @author 黄汉杰
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/html/**");
    }

    /**
     * 配置
     * @author 黄汉杰
     * @date 2022/4/13 0013 16:46
     * @param http   认证
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 放行所有 OPTIONS 请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 放行接口
                .antMatchers("/security/login").permitAll()
                // 其他请求都需要认证后才能访问
                .anyRequest().authenticated()
                // 投票器 accessDecisionManager
                .accessDecisionManager(accessDecisionManager())
                // 添加自定义 jwt 权限过滤器
                .and().addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 打开 SpringSecurity 的跨域
                .cors()
                // 关闭 CSRF
                .and().csrf().disable()
                // 关闭 Session 机制
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * 定义一个加密器（这里返回 BCryptPasswordEncoder 作为加密器）
     * @author 黄汉杰
     * @date 2022/4/13 0013 14:20
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 声明 Spring Security 自带的 AuthenticationManager 作为认证 Bean（调用它的 authenticate 方法）
     * @author 黄汉杰
     * @date 2022/4/13 0013 14:32
     * @return org.springframework.security.authentication.AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 配置 jwt 权限过滤器
     * @author 黄汉杰
     * @date 2022/4/14 0014 11:36
     * @return top.hanjie.security.filter.JwtAuthenticationFilter
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    /**
     * 使用一票反对策略放入两个投票器（一个根据页面表达式进行投票，也就是上面的放行，一个自定义投票器，用于鉴权）
     * @author 黄汉杰
     * @date 2022/4/21 0021 15:25
     * @return org.springframework.security.access.AccessDecisionManager
     */
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        // 构造一个新的 AccessDecisionManager 放入两个投票器
        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(new WebExpressionVoter(), accessDecisionProcessor());
        // 使用 一票反对 策略
        return new UnanimousBased(decisionVoters);
    }

    /**
     * 自定义权限投票
     * @author 黄汉杰
     * @date 2022/4/21 0021 10:57
     * @return org.springframework.security.access.AccessDecisionVoter<org.springframework.security.web.FilterInvocation>
     */
    @Bean
    public AccessDecisionVoter<FilterInvocation> accessDecisionProcessor() {
        return new PermissionVoter();
    }

}