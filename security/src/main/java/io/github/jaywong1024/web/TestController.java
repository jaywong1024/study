package io.github.jaywong1024.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author 黄汉杰
 */
@RestController
@RequestMapping("test")
public class TestController {


    @GetMapping("whatever")
    public String whatever() {
        return "任何人都可以访问";
    }

    @GetMapping("user")
    public String user() {
        return "只有 USER 允许访问";
    }

    @GetMapping("admin")
    public String admin() {
        return "只有 ADMIN 允许访问";
    }

}