package top.hanjie.design.create.product.motherboard;

import top.hanjie.design.create.product.cpu.AmdCpu;
import top.hanjie.design.create.product.cpu.Cpu;

public class AmdMotherBoard implements MotherBoard {
    @Override
    public boolean checkCpuSocket(Cpu cpu) {
        return cpu instanceof AmdCpu;
    }
}
