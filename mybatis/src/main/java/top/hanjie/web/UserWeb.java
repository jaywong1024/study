package top.hanjie.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.hanjie.dto.UserDto;
import top.hanjie.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserWeb {

    private final UserService userService;

    @PostMapping("save")
    public UserDto.Common.Out save(@RequestBody @Valid UserDto.Save.In in) {
        return this.userService.save(in);
    }

    @GetMapping("getOne/{id}")
    public UserDto.Common.Out getOne(@PathVariable(value = "id") Long id) {
        return this.userService.getById(id);
    }

}