package io.github.jaywong1024.design.behavioral.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {
    void pay(BigDecimal price);
}
