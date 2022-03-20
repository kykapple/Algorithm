import java.io.*;
import java.util.*;

public class Main {
    static int n, k, INF = 987654321;
    static Pair[] arr;
    static int[][][] dp;

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new Pair[n];
        dp = new int[n][n][k + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(x, y);

            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        System.out.println(solve(0, 1, k));
    }

    static int solve(int prev, int idx, int k) {
        if (idx == n - 1) return calcDist(arr[prev], arr[idx]);
        if(dp[prev][idx][k] != INF) return dp[prev][idx][k];

        if (k > 0) dp[prev][idx][k] = solve(prev, idx + 1, k - 1);
        dp[prev][idx][k] = Math.min(dp[prev][idx][k], solve(idx, idx + 1, k) + calcDist(arr[prev], arr[idx]));

        return dp[prev][idx][k];
    }

    static int calcDist(Pair a, Pair b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

}
