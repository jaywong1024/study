package io.github.jaywong1024.leetcode;

/**
 * 122. 买卖股票的最佳时机 II
 *
 * @author 黄汉杰
 * <p>描述：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/<p>
 * <p>创建时间：2023/3/12 11:28<p>
 */
public class Test122 {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}));
    }

    // 贪心算法，把赚钱的，也就是斜率向上的曲线，全部记录下来
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 以时间为 x 轴，价格为 y 轴，计算出斜率
            // 因为每次间隔一天，所以 x 轴为 x(n) = x(n-1) + 1
            // 公式 k = (y(n) - y(n-1)) / x(n) - x(n-1) 可以简化为 k = y(n) - y(n-1)
            int k = prices[i] - prices[i - 1];
            if (k > 0)
                maxProfit += k;
        }
        return maxProfit;
    }

}