package io.github.jaywong1024.design.create.factory.method;

import io.github.jaywong1024.design.create.product.cpu.AmdCpu;
import io.github.jaywong1024.design.create.product.cpu.Cpu;

public class AmdFactory implements CpuFactory {
    @Override
    public Cpu getCpu() {
        return new AmdCpu();
    }
}
