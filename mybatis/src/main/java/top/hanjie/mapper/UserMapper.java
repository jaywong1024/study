package top.hanjie.mapper;

import top.hanjie.dto.UserDto;
import top.hanjie.entity.User;

public interface UserMapper {

    User getOne(UserDto.GetOne.In in);
    void add(User user);
    void modify(User user);

}