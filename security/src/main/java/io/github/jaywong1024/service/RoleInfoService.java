package io.github.jaywong1024.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.jaywong1024.entity.RoleInfo;

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
     * @return java.util.List<io.github.jaywong1024.entity.RoleInfo>
     */
    List<RoleInfo> getByUserId(String userId);

}