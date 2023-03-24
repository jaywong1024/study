package top.hanjie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserDto {
    public static class Common {
        @Data
        public static class Out {
            private Long id;
            private String username;
            @JsonFormat(pattern = "yyyy-MM-dd")
            private Date birthday;
            private String avatarUrl;
        }
    }
    public static class GetOne {
        @Data
        public static class In {
            @NotNull(message = "dto.user.id.required")
            private Long id;
        }
    }
    public static class Save {
        @Data
        public static class In {
            private Long id;
            @NotBlank(message = "dto.user.username.required")
            private String username;
            @JsonFormat(pattern = "yyyy-MM-dd")
            private Date birthday;
            private String avatarUrl;
        }
    }
}