package top.hanjie.event;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanjie.publish.DemoPublisher;

import javax.annotation.Resource;

/**
 * 事件测试类
 *
 * @author 黄汉杰
 */
@SpringBootTest
public class EventTest {

    @Resource
    private DemoPublisher publisher;

    @Test
    public void doTest() {
        this.publisher.publish("test!!!");
    }

}