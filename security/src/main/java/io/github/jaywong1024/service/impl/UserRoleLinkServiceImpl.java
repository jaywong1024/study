package io.github.jaywong1024.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import io.github.jaywong1024.entity.UserRoleLink;
import io.github.jaywong1024.mapper.UserRoleLinkMapper;
import io.github.jaywong1024.service.UserRoleLinkService;

/**
 * 用户与角色关联接口实现
 *
 * @author 黄汉杰
 */
@Service
public class UserRoleLinkServiceImpl
        extends ServiceImpl<UserRoleLinkMapper, UserRoleLink>
        implements UserRoleLinkService {

}