package io.github.jaywong1024.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserDto {
    public static class Common {
        @Data
        public static class Out {
            /**
             * 主键
             */
            private Long id;
            /**
             * 用户名
             */
            private String username;
            /**
             * 生日
             */
            @JsonFormat(pattern = "yyyy-MM-dd")
            private Date birthday;
            /**
             * 头像 url
             */
            private String avatarUrl;
        }
    }
    public static class GetOne {
        @Data
        public static class In {
            /**
             * 主键
             */
            @NotNull(message = "dto.user.id.required")
            private Long id;
        }
    }
    public static class Save {
        @Data
        public static class In {
            /**
             * 主键
             */
            private Long id;
            /**
             * 用户名
             */
            @NotBlank(message = "dto.user.username.required")
            private String username;
            /**
             * 生日
             */
            @JsonFormat(pattern = "yyyy-MM-dd")
            private Date birthday;
            /**
             * 头像 url
             */
            private String avatarUrl;
        }
    }
}