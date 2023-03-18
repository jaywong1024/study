package top.hanjie.design.create.behavioral.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {
    void pay(BigDecimal price);
}
