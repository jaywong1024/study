package io.github.jaywong1024.service.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.jaywong1024.dto.UserDto;
import io.github.jaywong1024.entity.User;
import io.github.jaywong1024.mapper.UserMapper;
import io.github.jaywong1024.service.UserService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto.Common.Out save(UserDto.Save.In in) {
        Long id = in.getId();
        User user = BeanUtil.toBean(in, User.class);
        if (Objects.isNull(id)) {
            this.userMapper.add(user);
            id = user.getId();
        } else {
            this.userMapper.modify(user);
        }
        return this.getById(id);
    }

    @Override
    public UserDto.Common.Out getOne(UserDto.GetOne.In in) {
        User user = this.userMapper.getOne(in);
        if (Objects.nonNull(user)) {
            return BeanUtil.toBean(user, UserDto.Common.Out.class);
        }
        return null;
    }

    public UserDto.Common.Out getById(Long id) {
        UserDto.GetOne.In getOneIn = new UserDto.GetOne.In();
        getOneIn.setId(id);
        return this.getOne(getOneIn);
    }

}