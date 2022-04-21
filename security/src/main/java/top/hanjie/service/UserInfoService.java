package top.hanjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hanjie.entity.UserInfo;

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
     * @return top.hanjie.entity.UserInfo
     */
    UserInfo getByUsername(String username);

}