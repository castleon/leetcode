package dp;

public class LeetCode1049 {
    /**
     * 有一堆石头，每块石头的重量都是正整数。
     *
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
     */

    private static int lastStoneWeightII(int[] stones) {
        // 将问题转化为 => 将石头分成两堆，两堆的差最小，即一个堆中的石头重量最接近总重量的一半
        // 从而转化为背包问题
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int capacity = sum / 2;
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < n; i++) {
            int stone = stones[i];
            for (int j = capacity; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum - 2 * dp[capacity];
    }

    public static void main(String[] args) {
        int[] stones = {2 , 7, 4, 1, 8, 1};
        System.out.println(lastStoneWeightII(stones));
    }

}
