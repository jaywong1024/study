package io.github.jaywong1024.design.create.factory.abs;

import io.github.jaywong1024.design.create.product.cpu.Cpu;
import io.github.jaywong1024.design.create.product.cpu.IntelCpu;
import io.github.jaywong1024.design.create.product.motherboard.IntelMotherBoard;
import io.github.jaywong1024.design.create.product.motherboard.MotherBoard;

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
