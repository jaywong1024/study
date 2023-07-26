package io.github.jaywong1024.design.create.product.motherboard;

import io.github.jaywong1024.design.create.product.cpu.Cpu;
import io.github.jaywong1024.design.create.product.cpu.IntelCpu;

public class IntelMotherBoard implements MotherBoard {
    @Override
    public boolean checkCpuSocket(Cpu cpu) {
        return cpu instanceof IntelCpu;
    }
}
