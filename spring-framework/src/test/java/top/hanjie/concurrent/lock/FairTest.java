package top.hanjie.concurrent.lock;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平测试
 *
 * @author 黄汉杰
 * <p>描述：公平锁与非公平锁测试<p>
 * <p>创建时间：2023/3/4 22:11<p>
 */
public class FairTest {

    private static final Lock fair = new ReentrantLock(Boolean.TRUE);
    private static final Lock nonFair = new ReentrantLock(Boolean.FALSE);

    public static void test(Lock lock, String lockName, int num) {
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    Thread.sleep(10);
                    System.out.print(Thread.currentThread().getName() + " -> ");
                } catch (Exception ignored) { } finally {
                    lock.unlock();
                }
            }, lockName + i).start();
        }
        System.out.println();
    }

    @SneakyThrows
    public static void main(String[] args) {
        test(fair, "fair", 50);
        Thread.sleep(1000);
        test(nonFair, "nonFair", 50);
    }

}