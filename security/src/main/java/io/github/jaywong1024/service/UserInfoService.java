package io.github.jaywong1024.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.jaywong1024.entity.UserInfo;

/**
 * 用户接口
 * @author 黄汉杰
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 根据用户名获取
     * @author 黄汉杰
     * @date 2022/4/19 0019 18:08
     * @param username   用户名
     * @return io.github.jaywong1024.entity.UserInfo
     */
    UserInfo getByUsername(String username);

}