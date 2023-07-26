package io.github.jaywong1024.handler.msg.ext;

import org.springframework.stereotype.Component;
import io.github.jaywong1024.handler.msg.AbstractMsgHandler;

import java.util.Locale;

/**
 * 英文消息处理器
 *
 * @author 黄汉杰
 */
@Component
public class EnglishMsgHandler extends AbstractMsgHandler {

    @Override
    protected Locale getLocalLocale() {
        return Locale.ENGLISH;
    }

}