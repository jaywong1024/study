package io.github.jaywong1024.concurrent.base;

import lombok.SneakyThrows;

/**
 * 线程状态测试
 *
 * @author 黄汉杰
 */
public class ThreadStateTest {

    public static Runnable getRunnable(Object target, int num) {
        return () -> {
            try {
                synchronized (target) {
                    Thread.sleep(num);
                    System.out.println(Thread.currentThread().getName() + " over");
                }
            } catch (Exception ignored) {
            }
        };
    }

    @SneakyThrows
    public static void main(String[] args) {
        Object obj = new Object();
        Thread t1 = new Thread(getRunnable(obj, 20_000), "t1");
        Thread t2 = new Thread(getRunnable(obj, 20_000), "t2");
        t1.start();
        Thread.sleep(10);
        t2.start();
        t2.join();
        System.out.println(Thread.currentThread().getName() + " over");
    }

}