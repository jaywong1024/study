package top.hanjie.jvm.classinfo;

/**
 * 测试类
 *
 * @author 黄汉杰
 * <p>描述：测试类<p>
 * <p>创建时间：2023/2/24 17:13<p>
 */
public class Test implements TestApi {

    private static final Test test = new Test();

    public static void main(String[] args) {
        int a = test.a();
        System.out.println(a);
    }

    @Override
    public int a() { return this.b(); }
    @Override
    public int b() { return this.c(); }
    @Override
    public int c() {
        int a = 10, b = 20, c = 30;
        return a + b + c;
    }

}