package backtracking;

import java.util.ArrayList;
import java.util.List;

public class LeetCode216 {

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        search(res, new ArrayList<>(), k, n, 1);
        return res;
    }

    private static void search(List<List<Integer>> res, List<Integer> tmp, int k, int n, int num) {
        // k个数，可以从num开始取，和为n
        if (k == 0 && tmp.size() > 0 && n == 0 && num <= 10) {
            res.add(tmp);
        } else if (k > 0 && n >= num && num <= 9) {
            for (int i = num; i <= 9; i++) {
                List<Integer> tmp2 = new ArrayList<>(tmp);
                tmp2.add(i);
                search(res, tmp2, k-1, n-i, i+1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = combinationSum3(3, 9);
        for (List<Integer> list : result) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
