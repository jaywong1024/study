package top.hanjie.design.create.factory.abs;

import top.hanjie.design.create.product.cpu.AmdCpu;
import top.hanjie.design.create.product.cpu.Cpu;
import top.hanjie.design.create.product.motherboard.AmdMotherBoard;
import top.hanjie.design.create.product.motherboard.MotherBoard;

public class AmdFactory implements ComputerFactory {
    @Override
    public MotherBoard getMotherBoard() {
        return new AmdMotherBoard();
    }
    @Override
    public Cpu getCpu() {
        return new AmdCpu();
    }
}
