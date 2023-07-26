package io.github.jaywong1024.service;

import io.github.jaywong1024.dto.UserDto;

public interface UserService {
    UserDto.Common.Out save(UserDto.Save.In in);
    UserDto.Common.Out getOne(UserDto.GetOne.In in);
    UserDto.Common.Out getById(Long id);
}