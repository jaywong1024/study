package top.hanjie.security.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hanjie.security.dto.LoginDto;
import top.hanjie.security.jwt.JwtUtils;
import utils.ResultBean;
import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityController {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResultBean<LoginDto.Out> login(@RequestBody @Valid LoginDto.In in) {
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
        System.out.println("put cache");
        // 7.组装返回
        return ResultBean.ok(LoginDto.Out.builder().token(token).build());
    }

}