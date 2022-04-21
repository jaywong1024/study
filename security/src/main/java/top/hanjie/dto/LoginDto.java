package top.hanjie.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 登录传输类
 * @author 黄汉杰
 */
public class LoginDto {

    /**
     * 权限
     */
    @Data
    public static class Permission {
        /**
         * 权限名称
         */
        private String name;
        /**
         * 权限 url
         */
        private String url;
        /**
         * 方法类型
         */
        private String method;
    }

    /**
     * 角色
     */
    @Data
    public static class Role {
        /**
         * 角色名称
         */
        private String name;
        /**
         * 角色名称
         */
        private String code;
        /**
         * 角色名称
         */
        private String remark;
    }
    /**
     * 角色
     */
    @Data
    public static class User {
        /**
         * 用户名
         */
        private String username;
        /**
         * 昵称
         */
        private String nickname;
    }

    @Data
    public static class In {
        /**
         * 用户名
         */
        @NotBlank(message = "User name cannot be empty.")
        private String username;
        /**
         * 密码
         */
        @NotBlank(message = "Password cannot be empty.")
        private String password;
    }

    @Data
    @Builder
    public static class Out {
        /**
         * 令牌
         */
        private String token;
        /**
         * 用户信息
         */
        private User user;
        /**
         * 角色信息
         */
        private List<Role> roles;
        /**
         * 权限信息
         */
        private List<Permission> permissions;
    }

}