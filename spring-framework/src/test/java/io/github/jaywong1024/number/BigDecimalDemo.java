package io.github.jaywong1024.number;

import java.math.BigDecimal;

/**
 * BigDecimal 用法总结
 *
 * @author Jay
 * <p>创建时间：2023/7/21 0021 10:20<p>
 */
public class BigDecimalDemo {

    public static void recommendInstance() {
        // 这种写法会损失精度，不推荐
        BigDecimal error = new BigDecimal(0.123456789);
        System.out.println(error); // 0.12345678899999999733605449137030518613755702972412109375
        // 这种写法是可以的，但不推荐
        BigDecimal ok = new BigDecimal(123);
        System.out.println(ok); // 123
        // 推荐的实例化方式
        BigDecimal recommend = new BigDecimal("0.123456789");
        System.out.println(recommend); // 0.123456789
    }

    public static void main(String[] args) {
        // 推荐的实例化方式
        recommendInstance();

        // ROUND_DOWN：去除多余的小数
        BigDecimal bd1 = new BigDecimal("0.123456789").setScale(2, BigDecimal.ROUND_DOWN);
        System.out.println(bd1); // 0.12

        // ROUND_UP：保留小数，进位处理
        BigDecimal bd2 = new BigDecimal("0.123456789").setScale(2, BigDecimal.ROUND_UP);
        System.out.println(bd2); // 0.13

        // ROUND_CEILING：正数进位向上，负数舍位向上
        BigDecimal bd3 = new BigDecimal("2.3333333").setScale(2, BigDecimal.ROUND_CEILING);
        BigDecimal bd4 = new BigDecimal("-2.3333333").setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(bd3); // 2.34
        System.out.println(bd4); // -2.33

        // ROUND_FLOOR：正数舍位向下，负数进位向下
        BigDecimal bd5 = new BigDecimal("2.3333333").setScale(2, BigDecimal.ROUND_FLOOR);
        BigDecimal bd6 = new BigDecimal("-2.3333333").setScale(2, BigDecimal.ROUND_FLOOR);
        System.out.println(bd5); // 2.33
        System.out.println(bd6); // -2.34

        // ROUND_HALF_UP：四舍五入（舍去的 >= 0.5 时进位）
        BigDecimal bd7 = new BigDecimal("23.4549").setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal bd8 = new BigDecimal("2.3450").setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bd7); // 23.45
        System.out.println(bd8); // 2.35

        // ROUND_HALF_DOWN：五舍六入（舍去的 > 0.5 时进位，包括 0.51）
        BigDecimal bd9 = new BigDecimal("23.4567").setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal bd10 = new BigDecimal("23.4551").setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal bd11 = new BigDecimal("23.4550").setScale(2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(bd9); // 23.46
        System.out.println(bd10); // 23.46
        System.out.println(bd11); // 23.45

        // ROUND_HALF_EVEN：舍弃部分左边为偶数，则五舍六入；舍弃部分左边为奇数，则四舍五入
        BigDecimal bd12 = new BigDecimal("23.4250").setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal bd13 = new BigDecimal("23.4550").setScale(2, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(bd12); // 23.42
        System.out.println(bd13); // 23.46

        // ROUND_UNNECESSARY：不需要舍入
        BigDecimal bd14 = new BigDecimal("23.4550").setScale(4, BigDecimal.ROUND_UNNECESSARY);
        System.out.println(bd14); // 23.4550

        try {
            BigDecimal bd15 = new BigDecimal("23.4550").setScale(2, BigDecimal.ROUND_UNNECESSARY);
        } catch (ArithmeticException e) {
            System.out.println("不需要舍入，但是设置了舍入模式，会抛出异常 " + e);
        }

    }

}