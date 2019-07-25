package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode1024 {

    /**
     * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
     *
     * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
     *
     * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
     *
     * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
     * 输出：3
     * 解释：
     * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
     * 然后，按下面的方案重制比赛片段：
     * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
     * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
     */

    private static int videoStitching(int[][] clips, int T) {
        // 方法1:贪心，思路是每次寻找一个更大的范围，时间复杂度和片段数有关，假设片段数是m，复杂度是O(m*n)
        // int seg = 0;
        // int currMin = 0, currMax = 0;
        // while (currMax < T) {
        //     boolean extend = false;
        //     for (int[] clip : clips) {
        //         int min = clip[0];
        //         int max = clip[1];
        //         if (min <= currMin && max > currMax) {
        //             currMax = max;
        //             extend = true;
        //         }
        //     }
        //     if (!extend) return -1;
        //     currMin = currMax;
        //     seg++;
        // }
        // return seg;

        // 方法2:贪心，先按照第一个数排序，在相同的左值的区间中找最大的右区间
        Arrays.sort(clips, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int segs = 0;
        int currMax = 0;
        if (clips[0][0] > 0) return -1;
        int index = 0;
        while (index < clips.length) {
            if (currMax < clips[index][0]) return -1;
            int tmpMax = currMax;
            while (index < clips.length && clips[index][0] <= currMax) {
                tmpMax = Math.max(tmpMax, clips[index][1]);
                index++;
            }
            currMax = tmpMax;
            segs++;
            if (currMax >= T) return segs;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] clips = {
                {0, 1}, {6, 8}, {0, 2}, {5, 6}, {0, 4}, {0, 3}, {6, 7}, {1, 3}, {4, 7}, {1, 4}, {2, 5},
                {2, 6}, {3, 4}, {4, 5}, {5, 7}, {6, 9}
        };
        System.out.println(videoStitching(clips, 9));
    }

}
