package backtracking;

public class LeetCode526 {
    /**
     * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
     *
     * 第 i 位的数字能被 i 整除
     * i 能被第 i 位上的数字整除
     * 现在给定一个整数 N，请问可以构造多少个优美的排列？
     */

    public static int countArrangement(int N) {
        if (N < 1) return 0;

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i + 1;
        }
        return helper(0, nums, 0, N);
    }

    private static int helper(int count, int[] nums, int start, int N) {
        if (start == N) {
            return count + 1;
        }
        for (int i = start; i < N; i++) {
            if (nums[i] % (start + 1) == 0 || (start + 1) % nums[i] == 0) {
                swap(nums, i, start);
                count = helper(count, nums, start + 1, N);
                swap(nums, start, i);
            }
        }
        return count;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
//        int c = countArrangement(5);
//        System.out.println(c);
        char a = 'a';
        char A = Character.toUpperCase(a);
        System.out.println(a);
    }
}
