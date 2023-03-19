package top.hanjie.design.behavioral.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {
    void pay(BigDecimal price);
}
