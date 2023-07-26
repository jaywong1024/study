package io.github.jaywong1024.concurrent.lock;

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

    private static final Lock reentrantLock = new ReentrantLock();
    private static final Lock nonReentrantLock = new NonReentrantLock();

    @SneakyThrows
    public static void test(Lock lock, String lockName, int num, int max) {
        if (num <= max) {
            if (lock.tryLock()) {
                System.out.println(lockName + num + " 获取锁成功！");
            } else {
                System.out.println(lockName + num + " 获取锁失败！");
            }
            test(lock, lockName, ++num, max);
        } else {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        test(reentrantLock, "reentrantLock", 0, 5);
        test(nonReentrantLock, "nonReentrantLock", 0, 5);
    }

}