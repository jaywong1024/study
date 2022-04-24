package top.hanjie.web;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hanjie.bean.ResultBean;
import top.hanjie.entity.Test;
import top.hanjie.mapper.TestMapper;
import top.hanjie.service.TestService;

import javax.annotation.Resource;
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

}