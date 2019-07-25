package dp;

import java.util.ArrayList;
import java.util.HashMap;

public class LeetCode1027 {

    /**
     * 给定一个整数数组 A，返回 A 中最长等差子序列的长度。
     *
     * 回想一下，A 的子序列是列表 A[i_1], A[i_2], ..., A[i_k] 其中 0 <= i_1 < i_2 < ... < i_k <= A.length - 1。并且如果 B[i+1] - B[i]( 0 <= i < B.length - 1) 的值都相同，那么序列 B 是等差的。
     *
     * 输入：[9,4,7,2,10]
     * 输出：3
     * 解释：
     * 最长的等差子序列是 [4,7,10]。
     */

    private static int longestArithSeqLength(int[] A) {
        int max = 0;
        int min = 0;
        for(int i : A){
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int len = max - min;
        // 最大差
        int[][] dp1 = new int[A.length][len+1];
        int[][] dp2 = new int[A.length][len+1];
        int res = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = i-1; j >= 0; j--){
                int diff = A[i] - A[j];
                if(diff < 0){
                    diff = Math.abs(diff);
                    dp1[i][diff] = Math.max(dp1[j][diff] + 1, dp1[i][diff]);
                    res = Math.max(res, dp1[i][diff]);
                }
                else{
                    dp2[i][diff] = Math.max(dp2[j][diff] + 1, dp2[i][diff]);
                    res = Math.max(res, dp2[i][diff]);
                }
            }
        }
        return res + 1;
    }

    public static void main(String[] args) {
        int[] A = {22,8,57,41,36,46,42,28,42,14,9,43,27,51,0,0,38,50,31,60,29,31,20,23,37,53,27,1,47,42,28,31,10,35,39,
                12,15,6,35,31,45,21,30,19,5,5,4,18,38,51,10,7,20,38,28,53,15,55,60,56,43,48,34,53,54,55,14,9,56,52};
        System.out.println(longestArithSeqLength(A));
    }

}
