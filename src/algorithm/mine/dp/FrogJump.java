package algorithm.mine.dp;

import java.util.Arrays;

/**
 * @author john
 * @version v1.0
 * @date 2020/5/14 下午3:33
 * @description 在河上有一座独木桥，一只青蛙想沿着独木桥从河的一侧跳到另一侧。在桥上有一些石子，青蛙很讨厌踩在这些石子上。由于桥的长度和青蛙一次跳过的距离都是正整数，我们可以把独木桥上青蛙可能到达的点看成数轴上的一串整点：0，1，……，L（其中L是桥的长度）。坐标为0的点表示桥的起点，坐标为L的点表示桥的终点。青蛙从桥的起点开始，不停的向终点方向跳跃。一次跳跃的距离是s到t之间的任意正整数（包括s,t）。当青蛙跳到或跳过坐标为L的点时，就算青蛙已经跳出了独木桥。
 * 题目给出独木桥的长度L，青蛙跳跃的距离范围s,t，桥上石子的位置。你的任务是确定青蛙要想过河，最少需要踩到的石子数。
 */
public class FrogJump {
    private static void qSort(int[] arr, int low, int high) {
        int i = low;
        int j = high;
        if (i >= j) {
            return;
        }

        int pivot = arr[i];
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            arr[i] = arr[j];

            while (i < j && arr[i] <= pivot) {
                i++;
            }
            arr[j] = arr[i];
        }

        // 交换完成之后，将指标数据放回原列表，不一定是原位置，i已经变化
        arr[i] = pivot;
        qSort(arr, low, i - 1);
        qSort(arr, i + 1, high);
    }

    // 计算最大公约数
    private static int gcd(int a, int b) {
        if (a == 1 || b == 1) {
            return 1;
        }

        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        if (a % b == 0) {
            return b;
        } else {
            return gcd(a % b, b);
        }
    }

    private static void dp(int s, int t, int l, int[] stones) {
        qSort(stones, 0, stones.length - 1);

        // 处理空地
        int distance = s * t / gcd(s, t);
        for (int i = 1; i < stones.length; i++) {
            int dist = stones[i] - stones[i - 1];
            stones[i] = stones[i - 1] + dist % distance;
        }
        l = (l - stones[stones.length - 1]) % distance + stones[stones.length - 1];
        int[] arr = new int[l + t + 1];
        for (int i = 0; i < stones.length; i++) {
            arr[stones[i]] = 1;
        }

        if (s == t) {
            // 计算踩到石子的数量(特殊情况)
            int ans = 0;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] % s == 0) {
                    ans++;
                }
            }
            System.out.println(ans);
        } else {
            // 动态填表
            int[] f = new int[l + t + 1];
            Arrays.fill(f, l);
            //初始位置不存在石子
            f[0] = 0;
            for (int i = s; i <= l + t; i++) {
                for (int j = s; j <= t; j++) {
                    if (i >= j) {
                        f[i] = Math.min(f[i], f[i - j] + arr[i]);
                    }
                }
            }

            // 计算踩到石子的数量
            int ans = l;
            for (int i = l; i <= l + t; i++)
                ans = Math.min(ans, f[i]);

            System.out.println(ans);
        }


    }

    public static void main(String[] args) {
        int[] stones = {2, 3, 5, 6, 7};
        dp(2, 3, 10, stones);
    }
}
