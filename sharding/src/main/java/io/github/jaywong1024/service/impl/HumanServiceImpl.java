package io.github.jaywong1024.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.jaywong1024.utils.MsgUtils;
import io.github.jaywong1024.dto.HumanDto;
import io.github.jaywong1024.entity.Human;
import io.github.jaywong1024.mapper.HumanMapper;
import io.github.jaywong1024.service.HumanService;

import java.util.Objects;

/**
 * 人物信息接口实现
 *
 * @author 黄汉杰
 */
@Service
public class HumanServiceImpl
        extends ServiceImpl<HumanMapper, Human>
        implements HumanService {

    private HumanDto.Common.Out human2Out(Human human) {
        HumanDto.Common.Out out = BeanUtil.toBean(human, HumanDto.Common.Out.class);
        out.setSexDesc(MsgUtils.getMsg("sex" + out.getSex()));
        return out;
    }

    @Override
    public HumanDto.Common.Out getOne(String id) {
        Human human = this.baseMapper.selectById(id);
        if (Objects.nonNull(human)) {
            return human2Out(human);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public HumanDto.Common.Out add(HumanDto.Add.In in) {
        Human human = BeanUtil.toBean(in, Human.class);
        this.baseMapper.insert(human);
        return human2Out(human);
    }

}