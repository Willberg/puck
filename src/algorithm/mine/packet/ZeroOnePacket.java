package algorithm.mine.packet;

/**
 * @author john
 * @version v1.0
 * @date 2020/5/13 下午4:33
 * @description 有N件物品和一个容量为V的背包。第i件物品的重量是w[i]，价值是v[i]。求解将哪些物品装入背包可使这些物品的重量总和不超过背包容量，且价值总和最大。
 * <p>
 * 这是最基础的背包问题，特点是：每种物品仅有一件，可以选择放或不放。
 * 用子问题定义状态：即f[i][v]表示前i件物品恰放入一个容量为v的背包可以获得的最大价值。则其状态转移方程便是：
 * f[i][v]=max{ f[i-1][v], f[i-1][v-w[i]]+v[i] }。
 * 可以压缩空间，f[v]=max{f[v],f[v-w[i]]+v[i]}
 */
public class ZeroOnePacket {
    private static int[][] dp = null;
    private static int[] item = null;

    private static int[][] calValue(int[] w, int[] v, int c) {
        int[][] dp = new int[w.length][c + 1];
        for (int i = 1; i <= w.length - 1; i++) {
            for (int j = 1; j <= c; j++) {
                if (j < w[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }
        return dp;
    }

    private static void findWhat(int i, int j, int[] w, int[] v) {
        if (i >= 1) {
            if (dp[i][j] == dp[i - 1][j]) {
                item[i] = 0;
                findWhat(i - 1, j, w, v);
            } else if (j - w[i] >= 0 && dp[i][j] == dp[i - 1][j - w[i]] + v[i]) {
                item[i] = 1;
                findWhat(i - 1, j - w[i], w, v);
            }
        }
    }


    private static int calValueImproveSpace(int[] w, int[] v, int c) {
        int total = 0;
        for (int i = 0; i < c; i++) {

        }
        return total;
    }

    public static void main(String[] args) {
        int[] w = {0, 2, 4, 3, 5};
        int[] v = {0, 3, 5, 4, 6};
        int c = 8;
        dp = calValue(w, v, c);
        int total = dp[w.length - 1][c];
        System.out.println("total: " + total);

        System.out.println("table: ");
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < c + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }

        // 找出解
        System.out.println("bags: ");
        item = new int[w.length];
        findWhat(w.length - 1, c, w, v);
        for (int i : item) {
            System.out.print(i + " ");
        }
    }
}
