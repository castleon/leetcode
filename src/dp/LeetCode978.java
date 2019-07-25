package dp;

public class LeetCode978 {
    /**
     * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
     *
     * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
     * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
     * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
     *
     * 返回 A 的最大湍流子数组的长度。
     *
     * 输入：[9,4,2,10,7,8,8,1,9]
     * 输出：5
     * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
     */

    public int maxTurbulenceSize(int[] A) {
        if (A == null) return 0;

        int n = A.length;
        if (n == 1) return 1;

        int max = 1;
        int flag = compare(A[0], A[1]);
        if (flag != 2) max = 2;
        int count = max;
        for (int i = 1; i <= n-2; i++) {
            int tmpFlag = compare(A[i], A[i+1]);
            if (tmpFlag + flag == 0) {
                count++;
            } else {
                if (tmpFlag == 2) {
                    if (flag != 2) {
                        max = Math.max(count, max);
                        count = 1;
                    }
                } else {
                    if (flag == 2) {
                        count++;
                    } else {
                        max = Math.max(count, max);
                        count = 2;
                    }
                }
            }
            flag = tmpFlag;
        }
        max = Math.max(count, max);
        return max;
    }

    private int compare(int x, int y) {
        if (x > y) return 1;
        else if (x < y) return -1;
        else return 2;
    }

    public static void main(String[] args) {
        //
    }
}
