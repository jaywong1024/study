package io.github.jaywong1024.design.create.product.motherboard;

import io.github.jaywong1024.design.create.product.cpu.Cpu;

public interface MotherBoard {
    boolean checkCpuSocket(Cpu cpu);
}
