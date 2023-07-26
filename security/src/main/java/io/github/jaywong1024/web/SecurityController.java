package io.github.jaywong1024.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.jaywong1024.bean.ResultBean;
import io.github.jaywong1024.dto.LoginDto;
import io.github.jaywong1024.service.SecurityService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 鉴权控制器
 * @author 黄汉杰
 */
@RestController
@RequestMapping("security")
public class SecurityController {

    @Resource
    private SecurityService securityService;

    /**
     * 登录接口
     * @author 黄汉杰
     * @date 2022/4/14 0014 14:01
     * @param in   入参
     * @return io.github.jaywong1024.bean.ResultBean<io.github.jaywong1024.dto.LoginDto.Out>
     */
    @PostMapping("login")
    public ResultBean<LoginDto.Out> login(@RequestBody @Valid LoginDto.In in) {
        return ResultBean.ok(this.securityService.login(in));
    }

}