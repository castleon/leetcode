package dp;

import java.util.HashSet;

public class LeetCode983 {
    /**
     * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
     *
     * 火车票有三种不同的销售方式：
     *
     * 一张为期一天的通行证售价为 costs[0] 美元；
     * 一张为期七天的通行证售价为 costs[1] 美元；
     * 一张为期三十天的通行证售价为 costs[2] 美元。
     * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
     *
     * 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
     * 输出：11
     * 解释：
     * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
     * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
     * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
     * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
     * 你总共花了 $11，并完成了你计划的每一天旅行。
     */

    private static int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length-1];
        int[] dp = new int[lastDay+1];
        HashSet<Integer> hs = new HashSet<>();
        for (int d : days) {
            hs.add(d);
        }

        for (int i = 1; i < lastDay+1; i++) {
            if (hs.contains(i)) {
                if (i < 7) {
                    dp[i] = Math.min(dp[i-1] + costs[0], costs[1]);
                } else if (i < 30) {
                    dp[i] = Math.min(dp[i-1] + costs[0], dp[i-7] + costs[1]);
                } else {
                    dp[i] = Math.min(dp[i-1] + costs[0], dp[i-7] + costs[1]);
                    dp[i] = Math.min(dp[i], dp[i-30] + costs[2]);
                }
            } else {
                dp[i] = dp[i-1];
            }
        }

        for (int i : dp) {
            System.out.println(i);
        }
        return dp[lastDay];
    }

    public static void main(String[] args) {
        int[] days = {1,2,3,4,6,8,9,10,13,14,16,17,19,21,24,26,27,28,29};
        int[] costs = {3,14,50};
        System.out.println(mincostTickets(days, costs));
    }

}
