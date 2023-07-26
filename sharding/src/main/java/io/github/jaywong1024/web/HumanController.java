package io.github.jaywong1024.web;

import org.springframework.web.bind.annotation.*;
import io.github.jaywong1024.bean.ResultBean;
import io.github.jaywong1024.dto.HumanDto;
import io.github.jaywong1024.service.HumanService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 人物信息接口
 *
 * @author 黄汉杰
 */
@RestController
@RequestMapping("sharding/jdbc")
public class HumanController {

    @Resource
    private HumanService humanService;

    @GetMapping("human/{id}")
    public ResultBean<HumanDto.Common.Out> human(@PathVariable("id") String id) {
        return ResultBean.ok(this.humanService.getOne(id));
    }

    @PostMapping("human")
    public ResultBean<HumanDto.Common.Out> human(@RequestBody @Valid HumanDto.Add.In in) {
        return ResultBean.ok(this.humanService.add(in));
    }

}