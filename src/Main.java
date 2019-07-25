import java.util.*;

public class Main {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        boolean[] chosen = new boolean[nums.length];
        Arrays.sort(nums);
        permute(result, tmp, nums, chosen);
        return result;
    }

    private static void permute(List<List<Integer>> result, List<Integer> tmp, int[] nums, boolean[] chosen) {
        if (tmp.size() == nums.length) {
            // 注意， 这里一定要new，否则为空
            result.add(new ArrayList(tmp));
            return;
        }
        int i = 0;
        while (i < nums.length) {
            if (!chosen[i]) {
                tmp.add(nums[i]);
                chosen[i] = true;
                permute(result, tmp, nums, chosen);
                tmp.remove(tmp.size() - 1);
                chosen[i] = false;
                i++;
                while (i < nums.length && nums[i] == nums[i-1]) {
                    i++;
                }
            } else {
                i++;
            }
        }
    }

    public static void rotate(int[][] matrix) {
        int side = matrix.length;
        for (int i = 0; i < side/2; i++) {
            for (int j = 0; j < side - 2 * i -1; j++) {
                int tmp = matrix[i][i+j];
                matrix[i][i+j] = matrix[side-1-i-j][i];
                matrix[side-1-i-j][i] = matrix[side-1-i][side-1-i-j];
//                System.out.println(matrix);
                matrix[side-1-i][side-1-i-j] = matrix[i+j][side-1-i];
//                System.out.println(matrix.toString());
                matrix[i+j][side-1-i] = tmp;
//                System.out.println(matrix.toString());
            }
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String sortedStr = sorted(str);
            List<String> list = new ArrayList<>();
            if (map.containsKey(sortedStr)) {
                list = map.get(sortedStr);
                list.add(str);
            } else {
                list.add(str);
            }
            map.put(sortedStr, list);
        }
        res.addAll(map.values());
        return res;
    }

    private static String sorted(String str) {
        char[] c = str.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        search(res, n, new ArrayList<>());
        return res;
    }

    /*
     * colsNums: for each row, the queen's column index
     */
    private void search(List<List<String>> res, int n, List<Integer> colNums) {
        if (n == colNums.size()) {
            res.add(getSolution(colNums, n));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!isValid(colNums, col))
                continue;
            colNums.add(col);
            search(res, n, colNums);
            colNums.remove(colNums.size() - 1);
        }
    }

    private List<String> getSolution(List<Integer> colNums, int n) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append((j == colNums.get(i)) ? "Q" : ".");
            }
            res.add(sb.toString());
        }
        return res;
    }

    private static boolean isValid(List<Integer> colNums, int col) {
        int currentRow = colNums.size();
        for (int i = 0; i < currentRow; i++) {
            if (col == colNums.get(i)) return false;
            if (i + colNums.get(i) == currentRow + col) return false;
            if (colNums.get(i) - i == col - currentRow) return false;
        }
        return true;
    }

    public static int maxSubArray(int[] nums) {

        // O(n) solution
        /*
        int maxSoFar = nums[0];
        int maxEndHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndHere = Math.max(maxEndHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndHere);
        }
        return maxSoFar;
        */

        // divide and conquer
        return search(nums, 0, nums.length - 1);
    }

    private static int search(int[] nums, int start, int end) {
        if (start >= end) {
            return nums[start];
        }
        int mid = (start + end)/2;
        int max1 = search(nums, start, mid);
        int max2 = search(nums, mid+1, end);

        int leftMax = Integer.MIN_VALUE, lSum = 0;
        for (int i = mid; i >= start; i--) {
            lSum += nums[i];
            leftMax = Math.max(leftMax, lSum);
        }

        int rightMax = Integer.MIN_VALUE, rSum = 0;
        for (int i = mid+1; i <= end; i++) {
            rSum += nums[i];
            rightMax = Math.max(rightMax, rSum);
        }
        return Math.max(Math.max(max1, max2), leftMax + rightMax);

    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int n = s.length();
        int start = 0, end = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--){
            for (int j = i; j < n; j++) {
                if (j == i) {
                    dp[i][j] = true;
                } else if (j == i+1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j] && j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
//        List<List<Integer>> result = permuteUnique(new int[]{1, 1, 2});
//        System.out.println(result);
//        rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
//        groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(longestPalindrome("babad"));


    }
}
