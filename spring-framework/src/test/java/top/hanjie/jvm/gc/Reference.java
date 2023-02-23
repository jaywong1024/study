package top.hanjie.jvm.gc;

import lombok.SneakyThrows;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * 引用类型
 *
 * @author 黄汉杰
 * <p>描述：引用类型测试<p>
 * <p>创建时间：2023/2/23 15:25<p>
 */
public class Reference { }

// vm option: -XX:+PrintGCDetails -Xms10M -Xmx10M
class Soft {
    public static void main(String[] args) {
        // 软引用的构造方法中可以放入一个引用队列，如果被软引用的对象被垃圾回收了，软引用对象将会被放入引用队列中
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        SoftReference<Object> softReference = new SoftReference<>(new Object(), referenceQueue);
        // 打印一下软引用变量
        System.out.println(softReference);
        List<String> stringList = new ArrayList<>();
        try {
            while (Boolean.TRUE) stringList.add("只因");
        } catch (Throwable throwable) {
            System.out.println("查看 stringList 的容器元素数量: " + stringList.size());
            System.out.println("发生了内存溢出: " + throwable.getMessage());
            System.out.println("被软引用的对象: " + softReference.get());
            System.out.println("查看 referenceQueue 引用队列是否把 softReference 丢进去 ↓");
            System.out.println(referenceQueue.poll());
        }
    }
}

class Weak {
    @SneakyThrows
    public static void main(String[] args) {
        SoftReference<Object> softReference = new SoftReference<>(new Object());
        WeakReference<Object> weakReference = new WeakReference<>(new Object());
        System.out.println(softReference.get());
        System.out.println(weakReference.get());
        // 申请一次 full gc
        System.gc();
        System.out.println(softReference.get());
        System.out.println(weakReference.get());

        // WeakHashMap 是一种弱引用的 Map，如果 WeakHashMap 中的某个 key 对象没有其他引用，那么它在 gc 后会自动丢弃此键值对
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        // 注意这里不要直接 iKun = "油饼食不食" 赋值（因为 "油饼食不食" 会被丢到常量池中，并被 Weak 类静态引用）
        String iKun = new String("油饼食不食");
        weakHashMap.put(iKun, "荔枝");
        System.out.println(weakHashMap);

        // 将 new String("油饼食不食") 对象的引用断开
        iKun = null;
        System.gc();
        Thread.sleep(100);

        System.out.println(weakHashMap);
    }
}

class Phantom {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("我要寄啦！救救孩子！");
    }
    @SneakyThrows
    public static void main(String[] args) {
        ReferenceQueue<Phantom> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Phantom> phantomReference = new PhantomReference<>(new Phantom(), referenceQueue);
        System.out.println(phantomReference);
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                // 第三次循环的时候，向 JVM 申请 Full GC 将虚引用指向的 new Phantom() 收回掉
                System.gc();
                // 因为 Phantom 对象覆写了 finalize() 方法，JVM 也不知道它执行了 finalize() 方法还能不能与 GC Roots 建立引用链
                // 所以不能直接回收，需要执行 finalize() 方法
                // 又因为执行 finalize() 方法的线程优先级比较低，所以 gc 搞完了 finalize() 方法都还没执行
                System.out.println("Full GC 完成！");
                Thread.sleep(1000);
            }
            if (i == 3) {
                // 再向 JVM 申请 Full GC 一次，new Phantom() 肯定寄了
                // PhantomReference 对象就会被丢进 ReferenceQueue 队列了
                System.gc();
                Thread.sleep(1000);
            }
            System.out.println("引用队列: " + referenceQueue.poll());
        }
    }
}