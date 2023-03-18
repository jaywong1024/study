package top.hanjie.design.create.product.motherboard;

import top.hanjie.design.create.factory.method.CpuFactory;
import top.hanjie.design.create.factory.method.IntelFactory;
import top.hanjie.design.create.product.cpu.Cpu;

public class Test {
    public static void main(String[] args) {
        CpuFactory intelFactory = new IntelFactory();
        Cpu intelCpu = intelFactory.getCpu();
        // 输出：false
        System.out.println(new AmdMotherBoard().checkCpuSocket(intelCpu));
    }
}
