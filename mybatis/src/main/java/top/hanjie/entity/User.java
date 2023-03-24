package top.hanjie.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String username;
    private Date birthday;
    private String avatarUrl;
}