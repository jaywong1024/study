package io.github.jaywong1024.service;

import io.github.jaywong1024.dto.GoodsDto;

public interface GoodsService {

    GoodsDto.GetOne.Out getOne(GoodsDto.GetOne.In in);

    void save(GoodsDto.Save.In in);

}
