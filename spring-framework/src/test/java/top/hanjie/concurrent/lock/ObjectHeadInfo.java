package top.hanjie.concurrent.lock;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openjdk.jol.info.ClassLayout;

public class ObjectHeadInfo {
    @AllArgsConstructor
    static class SyncRunnable implements Runnable {
        private final Object obj;
        @Override
        @SneakyThrows
        public void run() {
            synchronized (obj) {
                Thread.sleep(10_000);
            }
        }
    }
    public static int rank(int i, int num, Object obj) {
        synchronized (obj) {
            return i < num ? rank(++i, num, obj) : i;
        }
    }
    @SneakyThrows
    public static void main(String[] args) {
        // 无锁
        Object obj1 = new Object();
        System.out.println("========== 无锁 ==========");
        System.out.println(ClassLayout.parseInstance(obj1).toPrintable());
        // 轻量级锁
        Object obj2 = new Object();
        synchronized (obj2) {
            System.out.println("========== 轻量级锁 ==========");
            System.out.println(ClassLayout.parseInstance(obj2).toPrintable());
        }
        // 重量级锁
        Object obj3 = new Object();
        new Thread(new SyncRunnable(obj3)).start();
        new Thread(new SyncRunnable(obj3)).start();
        new Thread(new SyncRunnable(obj3)).start();
        new Thread(new SyncRunnable(obj3)).start();
        System.out.println("========== 重量级锁 ==========");
        System.out.println(ClassLayout.parseInstance(obj3).toPrintable());
        // 偏向锁
        Object obj4 = new Object();
        rank(0, 999, obj4);
        System.out.println("========== 偏向锁 ==========");
        System.out.println(ClassLayout.parseInstance(obj4).toPrintable());
    }
}