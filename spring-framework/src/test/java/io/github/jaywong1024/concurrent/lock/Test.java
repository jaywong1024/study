package io.github.jaywong1024.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试类
 *
 * @author 黄汉杰
 * <p>描述：测试类<p>
 */
public class Test {

    private static Integer a = 6;
    public static void test1() {
        // 悲观锁，给 a 加锁
        synchronized (a) {
            a += 6;
        }
    }

    private static Integer b = 6;
    private static final Lock lock = new ReentrantLock();
    public static void test2() {
        // 悲观锁，给代码块加锁
        if (lock.tryLock()) {
            try {
                b += 6;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Get lock fail!");
        }
    }

    private static final AtomicInteger c = new AtomicInteger(6);
    public static void test3() {
        // 乐观锁，底层使用 cas 实现
        c.getAndAdd(6);
    }

}