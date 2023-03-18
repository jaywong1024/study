package top.hanjie.design.create.behavioral.strategy;

import java.math.BigDecimal;

public class Takeaway {
    public void checkout(PaymentStrategy payment, BigDecimal price) {
        payment.pay(price);
    }
    public static void main(String[] args) {
        Takeaway meituan = new Takeaway();
        // 在结账时选择支付宝
        meituan.checkout(new Alipay(), new BigDecimal("20"));
    }
}
