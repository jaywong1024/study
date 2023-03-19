package top.hanjie.design.behavioral.strategy;

import java.math.BigDecimal;

public class Wechatpay implements PaymentStrategy {
    @Override
    public void pay(BigDecimal price) {
        System.out.println(price + " paid with wechatpay");
    }
}
