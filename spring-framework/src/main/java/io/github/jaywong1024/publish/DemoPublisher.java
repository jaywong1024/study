package io.github.jaywong1024.publish;

import org.springframework.stereotype.Component;
import io.github.jaywong1024.event.DemoEvent;
import io.github.jaywong1024.utils.SpringContextUtils;

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