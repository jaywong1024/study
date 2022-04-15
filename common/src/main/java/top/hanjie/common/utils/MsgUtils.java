package top.hanjie.common.utils;

import top.hanjie.common.handler.msg.AbstractMsgHandler;

/**
 * 国际化多语种工具类
 *
 * @author 黄汉杰
 */
public class MsgUtils {

    public static String getMessage(String lg, String code, String... args) {
        // 1.根据语种获取消息处理器
        AbstractMsgHandler handler = AbstractMsgHandler.getHandler(lg);
        // 2.返回消息
        return handler.getMsg(code, args);
    }

}