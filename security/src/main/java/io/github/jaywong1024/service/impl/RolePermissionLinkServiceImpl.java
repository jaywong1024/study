package io.github.jaywong1024.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import io.github.jaywong1024.entity.RolePermissionLink;
import io.github.jaywong1024.mapper.RolePermissionLinkMapper;
import io.github.jaywong1024.service.RolePermissionLinkService;

/**
 * 角色与权限关联接口实现
 *
 * @author 黄汉杰
 */
@Service
public class RolePermissionLinkServiceImpl
        extends ServiceImpl<RolePermissionLinkMapper, RolePermissionLink>
        implements RolePermissionLinkService {

}