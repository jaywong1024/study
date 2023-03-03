package top.hanjie.concurrent.concurrent;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openjdk.jol.info.ClassLayout;

public class ObjectHeadInfo {
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
        synchronized (obj3) {
            System.out.println("========== 重量级锁 ==========");
            System.out.println(ClassLayout.parseInstance(obj1).toPrintable());
        }
    }
}
@AllArgsConstructor
class SyncRunnable implements Runnable {
    private final Object obj;
    @Override
    @SneakyThrows
    public void run() {
        synchronized (obj) {
            Thread.sleep(10_000);
        }
    }
}
