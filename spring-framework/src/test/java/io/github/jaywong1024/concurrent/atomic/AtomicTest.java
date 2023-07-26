package io.github.jaywong1024.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicTest {

    public static void testInt() {
        AtomicInteger i = new AtomicInteger(6);
        System.out.println("cur_i = " + i.getAndAdd(60));
        System.out.println("cur_i = " + i.getAndIncrement());
        System.out.println("cur_i = " + i.getAndDecrement());
        System.out.println("cur_i = " + i.getAndSet(777));
        System.out.println("cas result = " + i.compareAndSet(777, 888));
        System.out.println("cur_i = " + i.get());
    }

    static class Tools {
        static void printRefArr(AtomicReferenceArray<String> refArr) {
            for (int i = 0; i < refArr.length(); i++) {
                System.out.print(refArr.get(i));
            }
            System.out.println();
        }
    }
    public static void testRefArr() {
        AtomicReferenceArray<String> refArr = new AtomicReferenceArray<>(
                new String[]{"鸡", "你", "太", "美"});
        Tools.printRefArr(refArr);
        System.out.println(refArr.getAndSet(0, "只因"));
        System.out.println(refArr.getAndUpdate(1, s -> s + "实在是"));
        System.out.println(refArr.compareAndSet(2, "太", "钛"));
        System.out.println(refArr.updateAndGet(3, s -> "镁"));
        Tools.printRefArr(refArr);
    }

    public static void testRefStamp() {
        AtomicStampedReference<String> stampedRef
                = new AtomicStampedReference<>("盖亚！", 1);
        System.out.println(stampedRef.getReference());
        System.out.println(stampedRef.getStamp());
        stampedRef.compareAndSet(stampedRef.getReference(), "呀哈哈~",
                stampedRef.getStamp(), stampedRef.getStamp() + 1);
        System.out.println(stampedRef.getReference());
        System.out.println(stampedRef.getStamp());
    }

    static class Test {
        public volatile int intVal;
    }

    public static void testIntUpdater() {
        AtomicIntegerFieldUpdater<Test> intValUpdater = AtomicIntegerFieldUpdater
                .newUpdater(Test.class, "intVal");
        Test test = new Test();
        test.intVal = 1;
        System.out.println(intValUpdater.getAndIncrement(test));
        System.out.println(intValUpdater.get(test));
        System.out.println(intValUpdater.compareAndSet(test, 2, 6));
        System.out.println(intValUpdater.get(test));
    }

    public static void main(String[] args) {
        testInt();
        testRefArr();
        testRefStamp();
        testIntUpdater();
    }

}
