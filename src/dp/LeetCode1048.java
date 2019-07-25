package dp;

import java.util.ArrayList;

public class LeetCode1048 {

    /**
     * 给出一个单词列表，其中每个单词都由小写英文字母组成。
     *
     * 如果我们可以在 word1 的任何地方添加一个字母使其变成 word2，那么我们认为 word1 是 word2 的前身。例如，"abc" 是 "abac" 的前身。
     *
     * 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word_1 是 word_2 的前身，word_2 是 word_3 的前身，依此类推。
     *
     * 从给定单词列表 words 中选择单词组成词链，返回词链的最长可能长度。
     *
     * 输入：["a","b","ba","bca","bda","bdca"]
     * 输出：4
     * 解释：最长单词链之一为 "a","ba","bda","bdca"。
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 16
     * words[i] 仅由小写英文字母组成。
     */

    private static ArrayList<ArrayList<String>> list = new ArrayList<>();

    private static int maxLen = 1;

    private static int[][] dp = new int[16][1000];

    private static int longestStrChain(String[] words) {
        for (int i = 0; i < dp.length; i++) {
            list.add(new ArrayList<>());
        }
        for (String word : words) {
            list.get(word.length() - 1).add(word);
        }
        for (int i = 0; i < dp.length; i++) {
            ArrayList<String> tempList = list.get(i);
            if (tempList.size() == 0) continue;
            for (int j = 0; j < tempList.size(); j++) {
                dfs(tempList.get(j), i+1, 1, j);
            }
        }
        return maxLen;
    }

    /**
     * 已知当前字符串、当前长度、该字符串在同长度的列表中的索引，深度遍历长度+1的字符串
     * @param currStr       当前字符串
     * @param nextIndex     下一个索引
     * @param len           当前长度
     * @param subIndex      子索引
     * @return
     */
    private static int dfs(String currStr, int nextIndex, int len, int subIndex) {
        if (nextIndex == 16 || list.get(nextIndex).size() == 0) {
            if (maxLen < len) maxLen = len;
            return maxLen;
        } else {
            if (dp[nextIndex-1][subIndex] != 0) {
                return dp[nextIndex-1][subIndex];
            }
            ArrayList<String> nextList = list.get(nextIndex);
            int max = len;
            for (int i = 0; i < nextList.size(); i++) {
                String nextStr = nextList.get(i);
                if (checkPreStr(currStr.toCharArray(), nextStr.toCharArray())) {
                    dp[nextIndex][i] = dfs(nextStr, nextIndex+1, len+1, i);
                    if (dp[nextIndex][i] > max) max = dp[nextIndex][i];
                }
            }
            dp[nextIndex-1][subIndex] = max;
            if (maxLen < max) maxLen = max;
            return max;
        }
    }

    private static boolean checkPreStr(char[] preStr, char[] currStr) {
        int flag = 0;
        for (int i = 0, j = 0; i < preStr.length && j < currStr.length; i++, j++) {
            if (preStr[i] != currStr[j] && flag == 0) {
                flag = 1;
                i--;
            } else if (preStr[i] != currStr[j] && flag == 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
        System.out.println(longestStrChain(words));
    }

}
