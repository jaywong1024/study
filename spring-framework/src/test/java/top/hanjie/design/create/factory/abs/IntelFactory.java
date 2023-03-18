package top.hanjie.design.create.factory.abs;

import top.hanjie.design.create.product.cpu.Cpu;
import top.hanjie.design.create.product.cpu.IntelCpu;
import top.hanjie.design.create.product.motherboard.IntelMotherBoard;
import top.hanjie.design.create.product.motherboard.MotherBoard;

public class IntelFactory implements ComputerFactory {
    @Override
    public MotherBoard getMotherBoard() {
        return new IntelMotherBoard();
    }
    @Override
    public Cpu getCpu() {
        return new IntelCpu();
    }
}
