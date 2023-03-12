package top.hanjie.leetcode;

/**
 * 123. 买卖股票的最佳时机 III
 *
 * @author 黄汉杰
 * <p>描述：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/<p>
 * <p>创建时间：2023/3/12 11:54<p>
 */
public class Test123 {

    public static void main(String[] args) {
//        System.out.println(maxProfit1(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
//        System.out.println(maxProfit1(new int[]{1, 2, 3, 4, 5}));
//        System.out.println(maxProfit1(new int[]{7, 6, 4, 3, 1}));
        System.out.println(maxProfit2(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
    }

    public static int maxProfit1(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][5];
        dp[0][1] = dp[0][3] = -prices[0];
        dp[0][0] = dp[0][2] = dp[0][4] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][1] = Integer.max(-prices[i], dp[i - 1][1]);
            dp[i][2] = Integer.max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
            dp[i][3] = Integer.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
            dp[i][4] = Integer.max(dp[i - 1][3] + prices[i], dp[i - 1][4]);
        }
        return dp[len - 1][4];
    }

    public static int maxProfit2(int[] prices) {
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }

}