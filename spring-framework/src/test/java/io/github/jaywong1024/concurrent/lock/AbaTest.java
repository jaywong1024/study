package io.github.jaywong1024.concurrent.lock;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS ABA 问题测试
 *
 * @author 黄汉杰
 * <p>描述：CAS ABA 问题测试<p>
 */
public class AbaTest {

    @SneakyThrows
    public static void main(String[] args) {
        AtomicReference<String> reference = new AtomicReference<>("A");
        Thread t1 = new Thread(() -> {
            System.out.println("A -> B : " + reference.compareAndSet("A", "B"));
            System.out.println("B -> A : " + reference.compareAndSet("B", "A"));
        });
        t1.start();
        t1.join();
        System.out.println("A -> C : " + reference.compareAndSet("A", "C"));
        System.out.println("result -> " + reference.get());

        AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>("A", 1);
        int stamp = stampedReference.getStamp();
        Thread t2 = new Thread(() -> {
            int st1 = stampedReference.getStamp();
            System.out.println("A -> B : " + stampedReference
                    .compareAndSet("A", "B", st1, st1 + 1));
            int st2 = stampedReference.getStamp();
            System.out.println("B -> A : " + stampedReference
                    .compareAndSet("B", "A", st2, st2 + 1));
        });
        t2.start();
        t2.join();
        System.out.println("A -> C : " + stampedReference
                .compareAndSet("A", "C", stamp, stamp + 1));
        System.out.println("result -> " + stampedReference.getReference());
    }

}