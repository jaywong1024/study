package top.hanjie.concurrent.lock;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 共享锁
 *
 * @author 黄汉杰
 * <p>描述：共享锁测试<p>
 * <p>创建时间：2023/3/5 19:12<p>
 */
public class ShareLock {

    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void test(Lock lock, String lockName, int num, int interval) {
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                if (lock.tryLock()) {
                    try {
                        System.out.println(threadName + " got lock success!");
                        Thread.sleep(interval);
                    } catch (Exception ignored) { } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println(threadName + " got lock fail!");
                }
            }, lockName + i).start();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        // 测试写读：独占写 0.1s，再同时开 2 个线程并发读
        test(readWriteLock.writeLock(), "w1-", 1, 100);
        test(readWriteLock.readLock(), "r1-", 2, 0);
        // 测试读读：时间过了开 5 个线程并发读
        Thread.sleep(200);
        test(readWriteLock.readLock(), "r2-", 5, 0);
        Thread.sleep(200);
        // 测试读写：独占读锁 1s，同时开 3 个线程并发写
        test(readWriteLock.readLock(), "r3-", 1, 1000);
        test(readWriteLock.writeLock(), "w3-", 3, 100);
        // 测试写写：同时开 3 个线程并发写
        Thread.sleep(1100);
        test(readWriteLock.writeLock(), "w4-", 3, 100);
    }

}