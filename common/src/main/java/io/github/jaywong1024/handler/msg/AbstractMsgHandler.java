package io.github.jaywong1024.handler.msg;

import lombok.extern.slf4j.Slf4j;
import io.github.jaywong1024.utils.SpringContextUtils;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息处理器
 *
 * @author 黄汉杰
 */
@Slf4j
public abstract class AbstractMsgHandler {

    private static final Map<String, AbstractMsgHandler> HANDLERS = new ConcurrentHashMap<>();

    @PostConstruct
    public void initialize() {
        HANDLERS.put(this.getLocalLocale().getLanguage(), this);
    }

    /**
     * 获取对应语种的消息处理器（默认为中文）
     * @author 黄汉杰
     * @date 2022/4/15 0015 14:29
     * @param lg   语种
     * @return io.github.jaywong1024.handler.msg.AbstractMsgHandler
     */
    public static AbstractMsgHandler getHandler(String lg) {
        return HANDLERS.getOrDefault(lg, HANDLERS.get(Locale.CHINESE.getLanguage()));
    }

    /**
     * 获取消息
     * @author 黄汉杰
     * @date 2022/4/15 0015 14:24
     * @param code   编码
     * @param args   参数
     * @return java.lang.String
     */
    public String getMsg(String code, String... args) {
        try {
            return SpringContextUtils.getApplicationContext().getMessage(code, args, getLocalLocale());
        } catch (Exception e) {
            log.warn("Get msg error, code: {}, error: {}", code, e.getMessage());
        }
        return code;
    }

    /**
     * 获取对应语种的 local
     * @author 黄汉杰
     * @date 2022/4/15 0015 14:34
     * @return java.util.Locale
     */
    protected abstract Locale getLocalLocale();

}