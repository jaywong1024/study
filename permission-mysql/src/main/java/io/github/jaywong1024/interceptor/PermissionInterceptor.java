/*
 * Copyright (C), 2018-2022, 龙腾出行
 * FileName: PermissionInterceptor
 * Author:   dragonpass
 * Date:     2022/4/25 0025 13:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * dragonpass           13:46                       描述
 */
package io.github.jaywong1024.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import io.github.jaywong1024.entity.PermissionInfo;
import io.github.jaywong1024.service.PermissionInfoService;
import io.github.jaywong1024.utils.ThreadLocalUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 权限校验拦截器.
 *
 * @author 黄汉杰
 * <p>描述：<p>
 * <p>文件名称: PermissionInterceptor</p>
 * <p>创建时间：2022/4/25 0025 13:46<p>
 * Copyright (C), 2018-2022, 龙腾出行
 * @since 1.0.0
 * History:
 */

public class PermissionInterceptor implements HandlerInterceptor {

    @Resource
    private PermissionInfoService permissionInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.请求头拿取角色信息
        String role = request.getHeader("role");
        // 2.判断是否有数据权限
        if (StrUtil.isNotBlank(role)) {
            // 3.从缓存读取
            List<PermissionInfo> permissions = this.permissionInfoService
                    .getByRoleIds(Arrays.asList(role.split(",")));
            // 4.如果存则插入 thread local
            if (CollectionUtil.isNotEmpty(permissions)) {
                ThreadLocalUtils.set("permissions", permissions);
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        // 干掉 permissions
        ThreadLocalUtils.remove("permissions");
    }

}