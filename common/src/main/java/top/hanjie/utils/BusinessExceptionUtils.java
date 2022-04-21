package top.hanjie.utils;

import lombok.extern.slf4j.Slf4j;
import top.hanjie.exception.BusinessException;

import java.util.function.BooleanSupplier;

/**
 * 业务异常工具类
 *
 * @author 黄汉杰
 */
@Slf4j
public class BusinessExceptionUtils {

    /**
     * 断言为假
     *
     * @param fun  函数（返回 boolean）
     * @param msg  消息
     * @param args 参数
     * @author 黄汉杰
     * @date 2022/4/15 0015 11:53
     */
    public static void assertFalse(BooleanSupplier fun, String msg, String... args) {
        if (fun.getAsBoolean()) {
            throw new BusinessException(msg, args);
        }
    }

    /**
     * 断言为真
     *
     * @param fun  函数（返回 boolean）
     * @param msg  消息
     * @param args 参数
     * @author 黄汉杰
     * @date 2022/4/15 0015 11:53
     */
    public static void assertTrue(BooleanSupplier fun, String msg, String... args) {
        if (!fun.getAsBoolean()) {
            throw new BusinessException(msg, args);
        }
    }

}