package top.hanjie.service;

import top.hanjie.dto.GoodsDto;

public interface GoodsService {

    GoodsDto.GetOne.Out getOne(GoodsDto.GetOne.In in);

}
