package io.github.jaywong1024.mapper;

import io.github.jaywong1024.dto.UserDto;
import io.github.jaywong1024.entity.User;

public interface UserMapper {

    User getOne(UserDto.GetOne.In in);
    void add(User user);
    void modify(User user);

}