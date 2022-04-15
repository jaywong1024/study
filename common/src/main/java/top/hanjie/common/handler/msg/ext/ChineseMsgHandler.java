package top.hanjie.common.handler.msg.ext;

import org.springframework.stereotype.Component;
import top.hanjie.common.handler.msg.AbstractMsgHandler;

import java.util.Locale;

/**
 * 中文消息处理器
 *
 * @author 黄汉杰
 */
@Component(value = "zh")
public class ChineseMsgHandler extends AbstractMsgHandler {

    @Override
    protected Locale getLocalLocale() {
        return Locale.CHINESE;
    }

}