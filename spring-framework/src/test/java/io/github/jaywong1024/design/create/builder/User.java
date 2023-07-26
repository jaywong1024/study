package io.github.jaywong1024.design.create.builder;

import cn.hutool.core.date.DateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

// 建造者模式
@Builder
// 全参构造函数，私有
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    // 必填
    @lombok.NonNull
    private final String username;
    @lombok.NonNull
    private final String password;
    // 非必填
    private final Date birthday;
    private final String field1;
    private final String field2;
    private final String field3;
    private final String field4;

    public static void main(String[] args) {
        UserBuilder builder = User.builder();
        // 这里不填通不过 @lombok.NonNull 的校验
        builder.username("jay").password("pw");
        builder.birthday(DateUtil.parse("1998-02-21"))
                .field1("f1")
                .field2("f2")
                .field3("f3")
                .field4("f4");
        User jay = builder.build();
    }
}
