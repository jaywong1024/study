package io.github.jaywong1024.concurrent.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * CompletableFuture 学习
 *
 * @author Jay
 * <p>创建时间：2023/5/18 0018 10:45<p>
 */
public class CompletableFutureTest {

    // todo 完成 https://juejin.cn/post/6970558076642394142 学习

    private static final int CORE_POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 5;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    // 通过 ThreadPoolExecutor 构造函数自定义参数创建
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
            new ThreadPoolExecutor.AbortPolicy()
    );

    /**
     * 暂停指定时间，随后获得随机字符串
     * @author 黄汉杰
     * @date 2023/5/22 0022 15:43
     */
    @SneakyThrows
    public static String getRandomStr(int sleepSecond) {
        Thread.sleep(sleepSecond * 1000L);
        return RandomUtil.randomString(10);
    }

    /**
     * 测试异步任务（有返回值）
     * @author 黄汉杰
     * @date 2023/5/22 0022 15:43
     */
    @SneakyThrows
    public static void supplyAsyncExample() {
        System.out.println("开始时间: " + DateUtil.now());

        CompletableFuture<String> stringCompletableFuture5s
                = CompletableFuture.supplyAsync(() -> getRandomStr(5));
        CompletableFuture<String> stringCompletableFuture10s
                = CompletableFuture.supplyAsync(() -> getRandomStr(10));

        System.out.println("模拟主线程其他操作（暂停 5s）开始: " + DateUtil.now());
        Thread.sleep(5_000);
        System.out.println("模拟主线程其他操作（暂停 5s）结束: " + DateUtil.now());

        System.out.println("获取 stringCompletableFuture5s: "
                // 使用 get 获取字符串，最长等待 2s
                + stringCompletableFuture5s.get(2, TimeUnit.SECONDS));

        try {
            System.out.println("获取 stringCompletableFuture10s: "
                    // 使用 get 获取字符串，最长等待 3s（换成 5s 则不会被捕获异常）
                    + stringCompletableFuture10s.get(3, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            System.out.println("获取字符串超时" + e.getMessage());
        }

        System.out.println("结束时间: " + DateUtil.now());
    }

    /**
     * 测试异步任务（无返回值）
     * @author 黄汉杰
     * @date 2023/5/22 0022 15:45
     */
    @SneakyThrows
    public static void runAsyncExample() {
        System.out.println("开始时间: " + DateUtil.now());

        CompletableFuture.runAsync(() -> System.out.println(
                "执行 runAsync 获取字符串: " + getRandomStr(3) + " - " + DateUtil.now()));

        System.out.println("模拟主线程其他操作（暂停 5s）开始: " + DateUtil.now());
        Thread.sleep(5_000);
        System.out.println("模拟主线程其他操作（暂停 5s）结束: " + DateUtil.now());

        System.out.println("结束时间: " + DateUtil.now());
    }

    /**
     * 测试异步任务（接连执行，无入参无返回值）
     * @author 黄汉杰
     * @date 2023/5/22 0022 15:46
     */
    @SneakyThrows
    public static void thenRun() {
        System.out.println("开始时间: " + DateUtil.now());

        CompletableFuture.runAsync(() -> System.out.println(
                "执行 runAsync 获取字符串: " + getRandomStr(1) + " - " + DateUtil.now())
        ).thenRun(() -> System.out.println(
                "执行 thenRun 获取字符串: " + getRandomStr(2) + " - " + DateUtil.now())
        ).thenRunAsync(() -> System.out.println(
                "执行 thenRunAsync 获取字符串: " + getRandomStr(1) + " - " + DateUtil.now()));

        System.out.println("模拟主线程其他操作（暂停 5s）开始: " + DateUtil.now());
        Thread.sleep(5_000);
        System.out.println("模拟主线程其他操作（暂停 5s）结束: " + DateUtil.now());

        System.out.println("结束时间: " + DateUtil.now());
    }

    public static void main(String[] args) {
        System.out.println("======================== supplyAsyncExample ========================");
        supplyAsyncExample();
        System.out.println("======================== runAsyncExample ========================");
        runAsyncExample();
        System.out.println("======================== thenRun ========================");
        thenRun();
    }

}