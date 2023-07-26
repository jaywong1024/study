package io.github.jaywong1024.design.create.factory.method;

import io.github.jaywong1024.design.create.product.cpu.Cpu;
import io.github.jaywong1024.design.create.product.cpu.IntelCpu;

public class IntelFactory implements CpuFactory {
    @Override
    public Cpu getCpu() {
        return new IntelCpu();
    }
}
