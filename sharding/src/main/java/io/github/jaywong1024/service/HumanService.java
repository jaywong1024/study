package io.github.jaywong1024.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.jaywong1024.dto.HumanDto;
import io.github.jaywong1024.entity.Human;

/**
 * 人物信息接口
 *
 * @author 黄汉杰
 */
public interface HumanService extends IService<Human> {

    /**
     * 根据 id 获取单个人物信息
     * @author 黄汉杰
     * @date 2022/8/1 0001 16:53
     * @param id   主键
     * @return top.hanjjie.dto.HumanDto.Common.Out
     */
    HumanDto.Common.Out getOne(String id);

    /**
     * 新增人物信息
     * @author 黄汉杰
     * @date 2022/8/1 0001 16:58
     * @param in   入参
     * @return top.hanjjie.dto.HumanDto.Common.Out
     */
    HumanDto.Common.Out add(HumanDto.Add.In in);

}