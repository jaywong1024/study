package io.github.jaywong1024.algorithm;

import cn.hutool.core.date.DateUtil;

/**
 * 动态规划
 *
 * @author 黄汉杰
 * <p>描述：动态规划测试<p>
 * <p>创建时间：2023/3/9 22:20<p>
 */
public class DynamicProgrammingTest {



}
class Fib1 {
    public static long fib(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }
    public static void main(String[] args) {
        System.out.println(DateUtil.now());
        System.out.println(fib(6));
        System.out.println(DateUtil.now());
        System.out.println(fib(50));
        System.out.println(DateUtil.now());
        System.out.println(fib(100000));
    }
}

class Fib2 {
    public static long fib(int n) {
        if (n <= 0) return 0;
        long[] memo = new long[n + 1];
        for (int i = 0; i < n + 1; i++) {
            memo[i] = -1;
        }
        return fib(n, memo);
    }
    private static long fib(int n, long[] memo) {
        if (memo[n] != -1) return memo[n];
        if (n <= 2) return memo[n] = 1;
        return memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
    }
    public static void main(String[] args) {
        System.out.println(DateUtil.now());
        System.out.println(fib(6));
        System.out.println(DateUtil.now());
        System.out.println(fib(50));
        System.out.println(DateUtil.now());
        System.out.println(fib(60));
        System.out.println(DateUtil.now());
        System.out.println(fib(100000));
    }
}

class Fib3 {
    public static long fib(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        long a = 1, b = 1, result = 1;
        for (int i = 2; i < n; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(DateUtil.now());
        System.out.println(fib(6));
        System.out.println(DateUtil.now());
        System.out.println(fib(50));
        System.out.println(DateUtil.now());
        System.out.println(fib(60));
        System.out.println(DateUtil.now());
        System.out.println(fib(100000));
        System.out.println(DateUtil.now());
    }
}