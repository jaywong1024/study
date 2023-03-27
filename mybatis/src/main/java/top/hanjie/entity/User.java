package top.hanjie.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
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
    private Date birthday;
    /**
     * 头像 url
     */
    private String avatarUrl;
}