package top.hanjie.concurrent.lock;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 悲观乐观锁
 *
 * @author 黄汉杰
 * <p>描述：悲观乐观锁<p>
 */
public class ReentrantLockTest {

    private static final Lock lock = new ReentrantLock();
    static class LockRunnable implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            String threadName = Thread.currentThread().getName();
            if (lock.tryLock()) {
                try {
                    System.out.println(threadName + " 获取锁成功！");
                    Thread.sleep(1_000);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(threadName + " 获取锁失败！");
            }
        }
    }
    @SneakyThrows
    public static void testReentrantLock() {
        Thread thread1 = new Thread(new LockRunnable(), "Thread1");
        Thread thread2 = new Thread(new LockRunnable(), "Thread2");
        Thread thread3 = new Thread(new LockRunnable(), "Thread3");
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(2_000);
        LockRunnable lockRunnable = new LockRunnable();
        lockRunnable.run();
        lockRunnable.run();
        lockRunnable.run();
    }
    public static void main(String[] args) {
        testReentrantLock();
    }

}