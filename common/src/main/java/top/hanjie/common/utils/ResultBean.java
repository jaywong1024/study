package top.hanjie.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用请求返回
 * @author 黄汉杰
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 成功、失败
     */
    public static final int SUCCESS = 200;
    public static final int FAIL = 500;

    private Integer code;
    private String msg;
    private T data;

    public static <T> ResultBean<T> ok(T data) {
        return new ResultBean<>(SUCCESS, "success", data);
    }

    public static <T> ResultBean<T> error(String errMsg, T data) {
        return new ResultBean<>(FAIL, errMsg, data);
    }

}
