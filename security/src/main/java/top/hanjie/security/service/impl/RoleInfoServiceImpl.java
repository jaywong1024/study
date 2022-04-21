package top.hanjie.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hanjie.security.entity.RoleInfo;
import top.hanjie.security.mapper.RoleInfoMapper;
import top.hanjie.security.service.RoleInfoService;

/**
 * 角色接口实现
 *
 * @author 黄汉杰
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements RoleInfoService {

}