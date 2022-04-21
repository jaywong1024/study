package top.hanjie.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hanjie.security.entity.RoleInfo;

import java.util.List;

/**
 * 角色接口
 * @author 黄汉杰
 */
public interface RoleInfoService extends IService<RoleInfo> {

    /**
     * 获取角色信息
     * @author 黄汉杰
     * @date 2022/4/21 0021 16:08
     * @param userId   用户 id
     * @return java.util.List<top.hanjie.security.entity.RoleInfo>
     */
    List<RoleInfo> getByUserId(String userId);

}