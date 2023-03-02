package top.hanjie.concurrent.base;


import lombok.SneakyThrows;

/**
 * 守护线程测试
 *
 * @author 黄汉杰
 */
public class DaemonTest {

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3600 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setName("DamonTest007");
        // 必须在 start 方法前执行，否则会抛出 IllegalThreadStateException 异常
        thread.setDaemon(Boolean.TRUE);
        thread.start();
        // 这里必须阻塞主线程继续向下执行，否则主线程结束时守护线程也会寄
        Thread.sleep(3600 * 1000);
    }

}