package io.github.jaywong1024.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import io.github.jaywong1024.entity.RoleInfo;
import io.github.jaywong1024.entity.UserRoleLink;
import io.github.jaywong1024.enums.StatusEnum;
import io.github.jaywong1024.mapper.RoleInfoMapper;
import io.github.jaywong1024.service.RoleInfoService;
import io.github.jaywong1024.service.UserRoleLinkService;

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
        List<UserRoleLink> roleLink = this.userRoleLinkService.list(Wrappers.<UserRoleLink>lambdaQuery()
                .eq(UserRoleLink::getUserId, userId)
                .eq(UserRoleLink::getStatus, StatusEnum.NORMAL.getCode())
        );
        if (CollectionUtil.isNotEmpty(roleLink)) {
            result = this.list(Wrappers.<RoleInfo>lambdaQuery()
                    .in(RoleInfo::getId, roleLink.stream().map(UserRoleLink::getRoleId).collect(Collectors.toList()))
                    .eq(RoleInfo::getStatus, StatusEnum.NORMAL.getCode())
            );
        }
        return result;
    }

}