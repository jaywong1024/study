package io.github.jaywong1024.leetcode;

/**
 * 121. 买卖股票的最佳时机
 *
 * @author 黄汉杰
 * <p>描述：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/<p>
 * <p>创建时间：2023/3/12 8:43<p>
 */
public class Test121 {

    public static void main(String[] args) {
        System.out.println(maxProfit1(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit1(new int[]{7, 6, 4, 3, 1}));
        System.out.println(maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit2(new int[]{7, 6, 4, 3, 1}));
    }

    // 时间复杂度 O(n^2)
    public static int maxProfit1(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j < prices.length; j++) {
                if (prices[j] - prices[i] > result)
                    result = prices[j] - prices[i];
            }
        }
        return result;
    }

    // 时间复杂度 O(n)
    public static int maxProfit2(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            // 记录历史最低价位
            if (price < minPrice)
                minPrice = price;
            // 记录 最大收益 = 当天价格 - 历史最低价位
            else if (maxProfit < price - minPrice)
                maxProfit = price - minPrice;
        }
        return maxProfit;
    }

}