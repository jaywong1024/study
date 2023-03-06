package top.hanjie.concurrent.pool;

import cn.hutool.core.date.DateUtil;
import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试
 *
 * @author 黄汉杰
 * <p>描述：使用 ThreadPoolExecutor 测试线程池<p>
 * <p>创建时间：2023/3/7 0:00<p>
 */
public class PoolTest {

    static class RunTest implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start: " + DateUtil.now());
            Thread.sleep(10_000);
            System.out.println(Thread.currentThread().getName() + " end: " + DateUtil.now());
        }
    }

    private static final int CORE_POOL_SIZE = 1;
    private static final int MAX_POOL_SIZE = 2;
    private static final int QUEUE_CAPACITY = 5;
    private static final Long KEEP_ALIVE_TIME = 1L;

    @SneakyThrows
    public static void main(String[] args) {
        //通过 ThreadPoolExecutor 构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.AbortPolicy()
        );
        for (int i = 0; i < 15; i++) {
            try {
                executor.execute(new RunTest());
                Thread.sleep(1000);
            } catch (RejectedExecutionException ree) {
                System.out.println("reject " + i);
            }
        }
        executor.shutdown();
        while (!executor.isTerminated()) { }
        System.out.println("Finished all threads!");
    }

}