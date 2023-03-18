package top.hanjie.design.create.factory.method;

import top.hanjie.design.create.product.cpu.AmdCpu;
import top.hanjie.design.create.product.cpu.Cpu;

public class AmdFactory implements CpuFactory {
    @Override
    public Cpu getCpu() {
        return new AmdCpu();
    }
}
