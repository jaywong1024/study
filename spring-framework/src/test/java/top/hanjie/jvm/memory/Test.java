package top.hanjie.jvm.memory;

/**
 * 虚拟机栈测试类
 *
 * @author 黄汉杰
 * <p>描述：虚拟机栈测试类<p>
 * <p>创建时间：2023/1/6 20:33<p>
 */
public class Test {

    public static void main(String[] args) {
        int res = a();
        System.out.println(res);
    }

    public static int a() {
        return b();
    }

    public static int b() {
        return c();
    }

    public static int c() {
        int a = 10;
        int b = 20;
        return a + b;
    }

}