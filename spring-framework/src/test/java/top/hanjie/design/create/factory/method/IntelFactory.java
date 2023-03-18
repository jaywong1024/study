package top.hanjie.design.create.factory.method;

import top.hanjie.design.create.product.cpu.Cpu;
import top.hanjie.design.create.product.cpu.IntelCpu;

public class IntelFactory implements CpuFactory {
    @Override
    public Cpu getCpu() {
        return new IntelCpu();
    }
}
