package io.github.jaywong1024.web;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.annotation.*;
import io.github.jaywong1024.bean.ResultBean;
import io.github.jaywong1024.entity.PermissionInfo;
import io.github.jaywong1024.entity.Test;
import io.github.jaywong1024.mapper.TestMapper;
import io.github.jaywong1024.service.PermissionInfoService;
import io.github.jaywong1024.service.TestService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 测试接口.
 *
 * @author 黄汉杰
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private TestService testService;
    @Resource
    private TestMapper  testMapper;
    @Resource
    private PermissionInfoService permissionInfoService;

    @GetMapping("/list")
    public ResultBean<List<Test>> list() {
        return ResultBean.ok(this.testService.list(Wrappers.<Test>lambdaQuery().isNotNull(Test::getId)));
    }

    @GetMapping("/unionAll")
    public ResultBean<List<Test>> unionAll() {
        return ResultBean.ok(this.testMapper.unionAll());
    }

    @GetMapping("/join")
    public ResultBean<List<Test>> join() {
        return ResultBean.ok(this.testMapper.join());
    }

    @PostMapping("/add")
    public ResultBean<Test> add(@RequestBody Test test) {
        test.setStatus(0);
        test.setCreateId("1");
        test.setCreateTime(LocalDateTime.now());
        this.testService.save(test);
        return ResultBean.ok(test);
    }

    @GetMapping("getPermissionByRoleIds")
    public ResultBean<List<PermissionInfo>> getPermissionByRoleIds(String ids) {
       return ResultBean.ok(this.permissionInfoService.getByRoleIds(Arrays.asList(ids.split(","))));
    }

}