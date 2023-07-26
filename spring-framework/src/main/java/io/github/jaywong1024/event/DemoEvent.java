package io.github.jaywong1024.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 *
 * @author 黄汉杰
 */
@Getter
public class DemoEvent extends ApplicationEvent {

    private final String msg;

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

}