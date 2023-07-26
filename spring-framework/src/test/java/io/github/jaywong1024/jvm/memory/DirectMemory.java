package io.github.jaywong1024.jvm.memory;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存
 *
 * @author 黄汉杰
 * <p>描述：获取堆外内存测试<p>
 * <p>创建时间：2023/2/13 21:00<p>
 */
public class DirectMemory {

    @SneakyThrows
    public static void main(String[] args) {
        // 通过反射拿到 Unsafe 对象
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(Boolean.TRUE);
        Unsafe unsafe = (Unsafe) field.get(null);
        // 申请 4 字节内存空间
        long address = unsafe.allocateMemory(4);
        // 在申请内存地址写入 int 数据
        unsafe.putInt(address, 666666);
        // 打印申请内存地址上的 int 数据
        System.out.println(unsafe.getInt(address));
        // 释放申请的内存地址
        unsafe.freeMemory(address);
        // 再次打印申请内存地址上的 int 数据（这时数据已经冇得了）
        System.out.println(unsafe.getInt(address));
    }

}