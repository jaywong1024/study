package top.hanjie.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hanjie.security.entity.RolePermissionLink;
import top.hanjie.security.mapper.RolePermissionLinkMapper;
import top.hanjie.security.service.RolePermissionLinkService;

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