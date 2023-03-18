package top.hanjie.design.create.behavioral.strategy;

import java.math.BigDecimal;

public class Alipay implements PaymentStrategy {
    @Override
    public void pay(BigDecimal price) {
        System.out.println(price + " paid with alipay");
    }
}
