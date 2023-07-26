package io.github.jaywong1024.web;

import org.springframework.web.bind.annotation.*;
import io.github.jaywong1024.dto.GoodsDto;
import io.github.jaywong1024.service.GoodsService;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("goods")
public class GoodsWeb {

    @Resource
    private GoodsService goodsService;

    @GetMapping("getOne")
    public GoodsDto.GetOne.Out getOne(@RequestBody @Valid GoodsDto.GetOne.In in) {
        return this.goodsService.getOne(in);
    }

    @PostMapping("save")
    public void save(@RequestBody @Valid GoodsDto.Save.In in) {
        this.goodsService.save(in);
    }

}
