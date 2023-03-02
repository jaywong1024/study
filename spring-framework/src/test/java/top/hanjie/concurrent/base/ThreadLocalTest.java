package top.hanjie.concurrent.base;

import java.util.stream.IntStream;

/**
 * 本地线程变量测试
 *
 * @author 黄汉杰
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        ThreadLocal<Integer> intThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("HaHaHa");
        intThreadLocal.set(777);

        IntStream.range(0, 5).forEach(i -> new Thread(() -> {
            Thread currentThread = Thread.currentThread();
            stringThreadLocal.set(currentThread.getName() + ":" + i);
            printLocal(stringThreadLocal);
            stringThreadLocal.remove();
        }).start());

        printLocal(stringThreadLocal);
        printLocal(intThreadLocal);
        stringThreadLocal.remove();
        intThreadLocal.remove();
    }

    public static void printLocal(ThreadLocal<?> threadLocal) {
        System.out.println("Thread name: "
                + Thread.currentThread().getName()
                + ", local: "
                + threadLocal.get()
        );
    }

}