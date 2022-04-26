package top.hanjie.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.hanjie.entity.PermissionInfo;

import java.util.List;

/**
 * 权限接口
 * @author 黄汉杰
 */
public interface PermissionInfoService extends IService<PermissionInfo> {

    /**
     * 根据角色 id 获取权限
     * @author 黄汉杰
     * @date 2022/4/25 0025 11:45
     * @param ids   角色 id
     * @return java.util.List<top.hanjie.entity.PermissionInfo>
     */
    List<PermissionInfo> getByRoleIds(List<String> ids);

}