package top.hanjie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 手机
 *
 * @author Jay
 * 创建时间：2023/4/5 14:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    private String name;
    private String cpu;
    private String ram;
    private String rom;
}