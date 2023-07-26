package io.github.jaywong1024.web;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aop")
public class AopWeb {
    @GetMapping("test")
    public void test(@RequestParam(value = "ex", required = false) String ex) {
        if (StrUtil.isNotBlank(ex)) throw new RuntimeException("io.github.jaywong1024.web.AopWeb.test 异常");
        System.out.println("io.github.jaywong1024.web.AopWeb.test 执行中...");
    }
}