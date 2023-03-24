package top.hanjie.service;

import top.hanjie.dto.UserDto;

public interface UserService {
    UserDto.Common.Out save(UserDto.Save.In in);
    UserDto.Common.Out getOne(UserDto.GetOne.In in);
}