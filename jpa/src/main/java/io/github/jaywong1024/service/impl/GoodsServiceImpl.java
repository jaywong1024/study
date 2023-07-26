package io.github.jaywong1024.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import io.github.jaywong1024.dao.GoodsDao;
import io.github.jaywong1024.dto.GoodsDto;
import io.github.jaywong1024.entity.Goods;
import io.github.jaywong1024.service.GoodsService;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;
    @Override
    public GoodsDto.GetOne.Out getOne(GoodsDto.GetOne.In in) {
        return goodsDao.findById(in.getId())
                .map(value -> BeanUtil.toBean(value, GoodsDto.GetOne.Out.class))
                .orElse(null);
    }
    @Override
    @Transactional(rollbackOn = Throwable.class)
    public void save(GoodsDto.Save.In in) {
        this.goodsDao.save(BeanUtil.toBean(in, Goods.class));
    }
}
