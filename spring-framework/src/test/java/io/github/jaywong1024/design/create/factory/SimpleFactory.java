package io.github.jaywong1024.design.create.factory;

import io.github.jaywong1024.design.create.product.cpu.AmdCpu;
import io.github.jaywong1024.design.create.product.cpu.Cpu;
import io.github.jaywong1024.design.create.product.cpu.CpuType;
import io.github.jaywong1024.design.create.product.cpu.IntelCpu;

/**
 * 简单工厂
 *
 * @author 黄汉杰
 * <p>描述：简单工厂测试<p>
 * <p>创建时间：2023/3/17 19:01<p>
 */
public class SimpleFactory {
    public static Cpu getCpu(CpuType cpuType) {
        switch (cpuType) {
            case INTEL:
                return new IntelCpu();
            case AMD:
                return new AmdCpu();
            default:
                return null;
        }
    }
}

