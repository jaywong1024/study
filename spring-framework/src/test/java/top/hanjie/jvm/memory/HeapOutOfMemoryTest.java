package top.hanjie.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出测试
 * 启动配置：-Xms10M -Xmx10M -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author 黄汉杰
 * <p>描述：堆内存溢出测试<p>
 * <p>创建时间：2023/2/12 23:45<p>
 */
public class HeapOutOfMemoryTest {

    static class Test {}

    public static void main(String[] args) {
        List<Test> testList = new ArrayList<>();
        while (Boolean.TRUE) {
            testList.add(new Test());
        }
    }

}