package top.hanjie.security.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hanjie.security.entity.RoleInfo;
import top.hanjie.security.entity.UserRoleLink;
import top.hanjie.security.mapper.RoleInfoMapper;
import top.hanjie.security.service.RoleInfoService;
import top.hanjie.security.service.UserRoleLinkService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色接口实现
 *
 * @author 黄汉杰
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements RoleInfoService {

    @Resource
    private UserRoleLinkService userRoleLinkService;

    @Override
    public List<RoleInfo> getByUserId(String userId) {
        // 1.定义结果集
        List<RoleInfo> result = new ArrayList<>();
        // 2.获取用户角色列表
        List<UserRoleLink> roleLink = this.userRoleLinkService
                .list(Wrappers.<UserRoleLink>lambdaQuery().eq(UserRoleLink::getUserId, userId));
        if (CollectionUtil.isNotEmpty(roleLink)) {
            result = this.list(Wrappers.<RoleInfo>lambdaQuery().in(
                    RoleInfo::getId,
                    roleLink.stream().map(UserRoleLink::getRoleId).collect(Collectors.toList())
            ));
        }
        return result;
    }

}