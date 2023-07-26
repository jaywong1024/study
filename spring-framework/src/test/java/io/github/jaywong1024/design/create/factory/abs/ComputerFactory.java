package io.github.jaywong1024.design.create.factory.abs;

import io.github.jaywong1024.design.create.product.cpu.Cpu;
import io.github.jaywong1024.design.create.product.motherboard.MotherBoard;

public interface ComputerFactory {
    MotherBoard getMotherBoard();
    Cpu getCpu();
}
