package top.hanjie.concurrent.base;

import lombok.SneakyThrows;

/**
 * 锁测试
 *
 * @author 黄汉杰
 */
public class LockTest {

    private static final String res1 = "res_1";
    private static final String res2 = "res_2";

    @SneakyThrows
    public static void getRes1ThenRes2() {
        String threadName = Thread.currentThread().getName();
        synchronized (res1) {
            System.out.println(threadName + " get: " + res1);
            Thread.sleep(3000);
            synchronized (res2) {
                System.out.println(threadName + " get: " + res2);
            }
        }
    }

    @SneakyThrows
    public static void getRes2ThenRes1() {
        String threadName = Thread.currentThread().getName();
        synchronized (res2) {
            System.out.println(threadName + " get: " + res2);
            Thread.sleep(3000);
            synchronized (res1) {
                System.out.println(threadName + " get: " + res1);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(LockTest::getRes1ThenRes2).start();
        new Thread(LockTest::getRes2ThenRes1).start();
    }

}