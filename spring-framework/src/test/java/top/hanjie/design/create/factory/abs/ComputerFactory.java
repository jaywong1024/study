package top.hanjie.design.create.factory.abs;

import top.hanjie.design.create.product.cpu.Cpu;
import top.hanjie.design.create.product.motherboard.MotherBoard;

public interface ComputerFactory {
    MotherBoard getMotherBoard();
    Cpu getCpu();
}
