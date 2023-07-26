package io.github.jaywong1024.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 人物信息传输类
 *
 * @author 黄汉杰
 */
public class HumanDto {

    public static class Common {
        @Data
        public static class Out {
            /**
             * 主键
             */
            private String id;
            /**
             * 名字
             */
            private String name;
            /**
             * 地址
             */
            private String address;
            /**
             * 性别 0男 1女
             */
            private Integer sex;
            /**
             * 性别 - 描述
             */
            private String sexDesc;
        }
    }

    public static class Add {
        @Data
        public static class In {
            /**
             * 名字
             */
            @NotBlank(message = "Name is required")
            private String name;
            /**
             * 地址
             */
            @NotBlank(message = "Address is required")
            private String address;
            /**
             * 性别 0男 1女
             */
            @NotNull(message = "Sex is required")
            private Integer sex;
        }
    }

}