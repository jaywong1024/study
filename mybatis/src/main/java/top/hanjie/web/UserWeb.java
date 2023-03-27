package top.hanjie.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.hanjie.dto.UserDto;
import top.hanjie.service.UserService;

import javax.validation.Valid;

/**
 * 用户接口
 *
 * @author Jay
 * 创建时间：2023/3/27 20:03
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserWeb {

    private final UserService userService;

    /**
     * 保存用户信息
     * @author Jay
     * @date 2023/3/27 19:43
     */
    @PostMapping("save")
    public UserDto.Common.Out save(@RequestBody @Valid UserDto.Save.In in) {
        return this.userService.save(in);
    }

    /**
     * 根据 id 获取用户信息
     * @author Jay
     * @date 2023/3/27 19:45
     */
    @GetMapping("getOne/{id}")
    public UserDto.Common.Out getOne(@PathVariable(value = "id") Long id) {
        return this.userService.getById(id);
    }

}