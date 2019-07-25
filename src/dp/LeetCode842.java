package dp;

import java.util.ArrayList;
import java.util.List;

public class LeetCode842 {
    /**
     * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
     *
     * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
     *
     * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
     * F.length >= 3；
     * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
     * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
     *
     * 返回从 S 拆分出来的所有斐波那契式的序列块，如果不能拆分则返回 []。
     */
    private static List<Integer> splitIntoFibonacci(String s) {
        List<Integer> ret = new ArrayList<>();
        fibonacci(ret, new ArrayList<>(), 0, s);
        return ret;
    }

    private static void fibonacci(List<Integer> ret, List<Integer> temp, int start, String s) {
        if (ret.size() != 0) return;
        if (start == s.length() && temp.size() >= 3) {
            ret.addAll(temp);
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(start) == '0' && i > start) break;
            long curr = Long.parseLong(s.substring(start, i+1));
            if (curr > Integer.MAX_VALUE) break;
            int size = temp.size();
            if (size >= 2 && curr > temp.get(size - 1) + temp.get(size - 2)) break;
            else if (size < 2 || (curr == temp.get(size - 1) + temp.get(size - 2))) {
                temp.add((int)curr);
                fibonacci(ret, temp, i+1, s);
                temp.remove(temp.size() - 1);
            }
        }
    }

    // fangfa2
    private static List<Integer> splitIntoFibonacci2(String s) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < s.length() / 3 + 1; i++) {
            int first = getLegalInt(s.substring(0, i+1));
            if (first == -1) break;
            if (first == -2) break;
            ret.add(first);
            for (int j = i + 1; j < s.length() * 2 / 3 + 1; j++) {
                int second = getLegalInt(s.substring(i+1, j+1));
                if (second == -1) break;
                if (second == -2) break;
                ret.add(second);
                if (fibonacci2(ret, first, second, j+1, s)) {
                    return ret;
                }
                ret.remove(ret.size() - 1);
            }
            ret.remove(ret.size() - 1);
        }
        return ret;
    }

    private static boolean fibonacci2(List<Integer> ret, int first, int second, int start, String s) {
        if (start == s.length()) return true;
        long third = (long)first + (long)second;
        if (third > Integer.MAX_VALUE) return false;
        String str = String.valueOf(third);
        if (s.length() - start < str.length() || !s.substring(start, start + str.length()).equals(str)) return false;
        ret.add((int)third);
        if (fibonacci2(ret, second, (int)third, start + str.length(), s)) return true;
        ret.remove(ret.size() - 1);
        return false;
    }

    private static int getLegalInt(String s) {
        if (s.charAt(0) == '0' && s.length() >= 2) {
            return -1;
        }
        long l = Long.parseLong(s);
        if (l > Integer.MAX_VALUE) {
            return -2;
        } else {
            return (int)l;
        }
    }

    public static void main(String[] args) {
        List<Integer> result = LeetCode842.splitIntoFibonacci2("1320581321313221264343965566089105744171833277577");
        for (Integer i : result) {
            System.out.println(i);
        }
    }

}
