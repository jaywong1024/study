package top.hanjie.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hanjie.security.entity.PermissionInfo;

import java.util.List;
import java.util.Set;

/**
 * 权限接口
 * @author 黄汉杰
 */
public interface PermissionInfoService extends IService<PermissionInfo> {

    /**
     * 获取权限
     * @author 黄汉杰
     * @date 2022/4/21 0021 16:13
     * @param roleIds   角色id
     * @return java.util.Set<top.hanjie.security.entity.PermissionInfo>
     */
    Set<PermissionInfo> getByRoleIds(List<String> roleIds);

}