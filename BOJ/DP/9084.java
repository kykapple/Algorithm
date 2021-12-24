import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] coins = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            System.out.println(solve(coins, m));
        }
    }

    static int solve(int[] coins, int target) {
        int[][] dp = new int[coins.length + 1][target + 1];

        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= target; j++) {

                dp[i][j] = j < coins[i - 1]
                        ? dp[i - 1][j]
                        : dp[i - 1][j] + dp[i][j - coins[i - 1]];
            }
        }

        return dp[coins.length][target];
    }

}
