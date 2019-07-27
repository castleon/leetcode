package hash;

public class LeetCode974 {

    public static int subarraysDivByK(int[] A, int K) {
        int n = A.length;
        int[] count = new int[K];
        int sum_divided = 0;
        for (int i = 0 ; i < n; i++) {
            sum_divided = divide(sum_divided + A[i], K);
            count[sum_divided]++;
        }
        int res = count[0];
        for (int i = 0; i < K; i++) {
            if (count[i] > 1) {
                res += choose_two(count[i]);
            }
        }
        return res;
    }

    /**
     * Return 0~K-1
     */
    private static int divide(int x, int K) {
        while (x < K) x += K;
        return x % K;
    }

    private static int choose_two(int x) {
        return x*(x-1)/2;
    }

    public static void main(String[] args) {

    }
}
