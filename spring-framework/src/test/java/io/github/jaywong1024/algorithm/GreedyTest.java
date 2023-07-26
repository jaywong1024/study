package io.github.jaywong1024.algorithm;

import java.util.Objects;

/**
 * 贪心算法
 *
 * @author 黄汉杰
 * <p>描述：贪心算法<p>
 */
public class GreedyTest {

    private static final int LOW = 1;
    private static final int MID = 5 * LOW;
    private static final int HIGH = 20 * LOW;
    private static final int TOP = 25 * LOW;

    public static void change(int change) {
        System.out.println("========== " + change + " ==========");
        change(LOW, change(MID, change(HIGH, change(TOP, change))));
        System.out.println("========== " + change + " ==========");
    }

    public static int change(int quota, int change) {
        if (Objects.equals(change, 0)) return 0;
        int numOfQuota = 0;
        while (change >= quota) {
            numOfQuota++;
            change -= quota;
        }
        System.out.println("num of " + quota + " : " + numOfQuota);
        return change;
    }

    public static void main(String[] args) {
        change(41);
        change(46);
        change(75);
    }

}