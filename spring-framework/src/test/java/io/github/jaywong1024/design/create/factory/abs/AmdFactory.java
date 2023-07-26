package io.github.jaywong1024.design.create.factory.abs;

import io.github.jaywong1024.design.create.product.cpu.AmdCpu;
import io.github.jaywong1024.design.create.product.cpu.Cpu;
import io.github.jaywong1024.design.create.product.motherboard.AmdMotherBoard;
import io.github.jaywong1024.design.create.product.motherboard.MotherBoard;

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
