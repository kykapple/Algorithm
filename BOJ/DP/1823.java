import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());

            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(0, n - 1, 1));
    }

    static int solve(int left, int right, int turn) {
        if (left > right) {
            return 0;
        }
        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        dp[left][right] = solve(left + 1, right, turn + 1) + (turn * arr[left]);
        dp[left][right] = Math.max(dp[left][right], solve(left, right - 1, turn + 1) + (turn * arr[right]));

        return dp[left][right];
    }

}
