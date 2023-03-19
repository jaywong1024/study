package top.hanjie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import top.hanjie.dao.GoodsDao;
import top.hanjie.dto.GoodsDto;
import top.hanjie.service.GoodsService;

import javax.annotation.Resource;

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
}
