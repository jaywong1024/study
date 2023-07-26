package io.github.jaywong1024.concurrent.lock;

import lombok.Getter;
import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * CAS 测试
 *
 * @author 黄汉杰
 * <p>描述：CAS 测试<p>
 */
@Getter
public class CasTest {

    private volatile String name;
    private volatile int intVal;
    private volatile long longVal;

    public CasTest() {
    }

    private boolean compareAndSetName(String expect, String update) {
        return unsafe.compareAndSwapObject(this, nameOffset, expect, update);
    }

    private boolean compareAndIncrementIntVal(int expect) {
        return unsafe.compareAndSwapInt(this, intValOffset, expect, expect + 1);
    }

    private boolean compareAndIncrementLongVal(long expect) {
        return unsafe.compareAndSwapLong(this, longValOffset, expect, expect + 1);
    }

    private static Unsafe unsafe;
    private static long nameOffset;
    private static long intValOffset;
    private static long longValOffset;

    static {
        // 通过反射拿到 Unsafe 对象
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(Boolean.TRUE);
        try {
            unsafe = (Unsafe) field.get(null);
            nameOffset = unsafe.objectFieldOffset
                    (CasTest.class.getDeclaredField("name"));
            intValOffset = unsafe.objectFieldOffset
                    (CasTest.class.getDeclaredField("intVal"));
            longValOffset = unsafe.objectFieldOffset
                    (CasTest.class.getDeclaredField("longVal"));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        // 创建一个 CasTest 对象
        CasTest casTest = new CasTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("Thread name: " + Thread.currentThread().getName());
                System.out.println("change name result: " + casTest.compareAndSetName(null, "鸡你太美"));
                System.out.println("int++ result: " + casTest.compareAndIncrementIntVal(casTest.getIntVal()));
                System.out.println("long++ result: " + casTest.compareAndIncrementLongVal(casTest.getLongVal()));
            }, "t" + i).start();
        }
        Thread.sleep(1_000);
        System.out.println("name: " + casTest.getName());
        System.out.println("intVal: " + casTest.getIntVal());
        System.out.println("longVal: " + casTest.getLongVal());
    }

}