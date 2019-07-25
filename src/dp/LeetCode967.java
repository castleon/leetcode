package dp;

import java.util.ArrayList;

public class LeetCode967 {
    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1) {
            // 特例
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            res.add(i);
        }
        for (int i =  1; i < N; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int x : res) {
                int digit = x % 10;
                if (digit - K >= 0) {
                    tmp.add(x * 10 + digit - K);
                }
                // 避免添加重复
                if (K != 0 && digit + K < 10) {
                    tmp.add(x * 10 + digit + K);
                }
            }
            res = tmp;
        }
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }
}
