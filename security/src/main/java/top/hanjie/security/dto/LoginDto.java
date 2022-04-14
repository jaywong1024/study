package top.hanjie.security.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录传输类
 * @author 黄汉杰
 */
public class LoginDto {

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
    }

}