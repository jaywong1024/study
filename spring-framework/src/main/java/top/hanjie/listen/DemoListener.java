package top.hanjie.listen;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import top.hanjie.event.DemoEvent;

/**
 * 事件监听类
 *
 * @author 黄汉杰
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    @Override
    public void onApplicationEvent(DemoEvent event) {
        System.out.println("Listener event msg: " + event.getMsg());
    }

}