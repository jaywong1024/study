package top.hanjie.publish;

import org.springframework.stereotype.Component;
import top.hanjie.event.DemoEvent;
import top.hanjie.utils.SpringContextUtils;

/**
 * 事件发布类
 *
 * @author 黄汉杰
 */
@Component
public class DemoPublisher {

    public void publish(String msg) {
        SpringContextUtils.getApplicationContext().publishEvent(new DemoEvent(this, msg));
    }

}