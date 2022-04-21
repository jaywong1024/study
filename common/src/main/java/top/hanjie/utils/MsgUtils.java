package top.hanjie.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.hanjie.handler.msg.AbstractMsgHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Objects;

/**
 * 国际化多语种工具类
 *
 * @author 黄汉杰
 */
public class MsgUtils {

    /**
     * 获取语种（请求头 Accept-Language）
     *
     * @return java.lang.String
     * @author 黄汉杰
     * @date 2022/4/21 0021 17:22
     */
    public static String getLg() {
        if (Objects.isNull(RequestContextHolder.getRequestAttributes())) {
            return Locale.CHINESE.getLanguage();
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes())).getRequest();
        return StrUtil.isNotBlank(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE))
                ? request.getHeader(HttpHeaders.ACCEPT_LANGUAGE) : Locale.CHINESE.getLanguage();
    }

    /**
     * 获取消息（自动获取语种）
     *
     * @param code 编码
     * @param args 参数
     * @return java.lang.String
     * @author 黄汉杰
     * @date 2022/4/21 0021 17:56
     */
    public static String getMsg(String code, String... args) {
        return getMessage(getLg(), code, args);
    }

    /**
     * 获取消息
     *
     * @param lg   语种
     * @param code 编码
     * @param args 参数
     * @return java.lang.String
     * @author 黄汉杰
     * @date 2022/4/21 0021 17:56
     */
    public static String getMessage(String lg, String code, String... args) {
        // 1.根据语种获取消息处理器
        AbstractMsgHandler handler = AbstractMsgHandler.getHandler(lg);
        // 2.返回消息
        return handler.getMsg(code, args);
    }

}