package top.hanjie.design.create.product.motherboard;

import top.hanjie.design.create.product.cpu.Cpu;
import top.hanjie.design.create.product.cpu.IntelCpu;

public class IntelMotherBoard implements MotherBoard {
    @Override
    public boolean checkCpuSocket(Cpu cpu) {
        return cpu instanceof IntelCpu;
    }
}
