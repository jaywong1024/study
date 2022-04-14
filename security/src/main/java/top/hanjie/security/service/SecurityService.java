package top.hanjie.security.service;

import top.hanjie.security.dto.LoginDto;

/**
 * 权限校验接口
 * @author 黄汉杰
 */
public interface SecurityService {

    /**
     * 登录接口
     * @author 黄汉杰
     * @date 2022/4/14 0014 13:59
     * @param in   入参
     * @return top.hanjie.security.dto.LoginDto.Out
     */
    LoginDto.Out login(LoginDto.In in);

}