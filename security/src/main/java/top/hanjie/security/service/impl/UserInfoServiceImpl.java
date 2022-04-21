package top.hanjie.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hanjie.security.entity.UserInfo;
import top.hanjie.security.mapper.UserInfoMapper;
import top.hanjie.security.service.UserInfoService;

/**
 * 用户接口实现
 *
 * @author 黄汉杰
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public UserInfo getByUsername(String username) {
        return this.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUsername, username));
    }

}