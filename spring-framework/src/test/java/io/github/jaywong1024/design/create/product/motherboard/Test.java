package io.github.jaywong1024.design.create.product.motherboard;

import io.github.jaywong1024.design.create.factory.method.CpuFactory;
import io.github.jaywong1024.design.create.factory.method.IntelFactory;
import io.github.jaywong1024.design.create.product.cpu.Cpu;

public class Test {
    public static void main(String[] args) {
        CpuFactory intelFactory = new IntelFactory();
        Cpu intelCpu = intelFactory.getCpu();
        // 输出：false
        System.out.println(new AmdMotherBoard().checkCpuSocket(intelCpu));
    }
}
