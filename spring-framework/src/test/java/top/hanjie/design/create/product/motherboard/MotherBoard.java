package top.hanjie.design.create.product.motherboard;

import top.hanjie.design.create.product.cpu.Cpu;

public interface MotherBoard {
    boolean checkCpuSocket(Cpu cpu);
}
