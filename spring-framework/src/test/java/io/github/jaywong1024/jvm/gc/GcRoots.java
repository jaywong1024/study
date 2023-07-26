package io.github.jaywong1024.jvm.gc;

public class GcRoots {

    // 2.方法区中类的静态属性引用
    public static final Integer CLASS_HEAD = 0xCAFEBABE;
    // 3.方法区中常量池中的对象的引用
    private String ikun = "只因";

    public static void main(String[] args) {
        // 1.虚拟机栈的栈帧中的局部变量
        Integer localInt = 6;
        GcRoots localGcRoots = new GcRoots();
        // 5.被锁（synchronized）持有的对象的引用
        Object objA = "你干嘛啊，哎哟";
        Object objB = objA;
        synchronized (objA) {
            System.out.println(objA);
        }
    }

}
