package io.github.jaywong1024.design.create.product.motherboard;

import io.github.jaywong1024.design.create.product.cpu.AmdCpu;
import io.github.jaywong1024.design.create.product.cpu.Cpu;

public class AmdMotherBoard implements MotherBoard {
    @Override
    public boolean checkCpuSocket(Cpu cpu) {
        return cpu instanceof AmdCpu;
    }
}
