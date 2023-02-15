package top.hanjie.jvm.gc;

import lombok.SneakyThrows;

/**
 * 最终判定
 *
 * @author 黄汉杰
 * <p>描述：最终判定测试<p>
 * <p>创建时间：2023/2/15 0015 11:02<p>
 */
public class FinalDetermine {

    public static Test test;

    public static class Test {
        @Override
        protected void finalize() throws Throwable {
            // 打印一下执行线程
            System.out.println(Thread.currentThread());
            // 自我救赎一下，与方法区中类的静态属性引用建立引用链
            test = this;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        // test 变量指向一个新的 Test 对象
        test = new Test();
        // 将变量赋值为 null
        test = null;
        // 申请执行一次 full gc（不是每次都执行，但一般都会执行）
        System.gc();
        // 休眠一下等待 finalize 执行
        // 这里不休眠的话，下面的打印结果可能为 null，因为从一个对象被判定为不可达开始，到 finalize() 方法被执行，所花费的时长是任意的
        Thread.sleep(100);
        // 打印结果查看 test 是否被回收
        System.out.println(test);
        // 再次将变量赋值为 null 并且申请 gc 查看 test 是否被回收
        test = null;
        System.gc();
        Thread.sleep(100);
        System.out.println(test);
    }

}