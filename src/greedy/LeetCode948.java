package greedy;

import java.util.Arrays;

public class LeetCode948 {

    /**
     * 你的初始能量为 P，初始分数为 0，只有一包令牌。
     *
     * 令牌的值为 token[i]，每个令牌最多只能使用一次，可能的两种使用方法如下：
     *
     * 如果你至少有 token[i] 点能量，可以将令牌置为正面朝上，失去 token[i] 点能量，并得到 1 分。
     * 如果我们至少有 1 分，可以将令牌置为反面朝上，获得 token[i] 点能量，并失去 1 分。
     * 在使用任意数量的令牌后，返回我们可以得到的最大分数。
     *
     * 示例 1：
     * 输入：tokens = [100], P = 50
     * 输出：0
     *
     * 示例 2：
     * 输入：tokens = [100,200], P = 150
     * 输出：1
     *
     * 示例 3：
     * 输入：tokens = [100,200,300,400], P = 200
     * 输出：2
     */

    /**
     * @param tokens    令牌
     * @param P         初始能量
     */
    private static int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int n = tokens.length;
        int i = 0, j = n-1;
        int score = 0;
        while(i <= j) {
            // 优先判断i的范围，防止i溢出
            while(i <= j && tokens[i] <= P) {
                P -= tokens[i];
                i++; score++;
            }
            // 至少要两个令牌，否则替换一个得分不变
            if (score >= 1 && i <= j-2) {
                score--;
                P += tokens[j];
                j--;
            } else {
                break;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        int[] tokens = {100, 200, 300, 400};
        System.out.println(bagOfTokensScore(tokens, 200));
    }
}
