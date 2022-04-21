package top.hanjie.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import top.hanjie.security.entity.PermissionInfo;

import java.util.List;

/**
 * 权限接口
 * @author 黄汉杰
 */
public interface PermissionInfoService extends IService<PermissionInfo> {

    /**
     * 根据用户 id 获取权限
     * @author 黄汉杰
     * @date 2022/4/20 0020 17:16
     * @param userId   用户id
     * @return java.util.List<org.springframework.security.core.authority.SimpleGrantedAuthority>
     */
    List<SimpleGrantedAuthority> getByUserId(String userId);

}