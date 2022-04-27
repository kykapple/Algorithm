import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        dp = new int[n + 1][40001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 40000; j++) {
                if (arr[i - 1] < j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - arr[i - 1]]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][arr[i - 1] - j]);
                }

                if (arr[i - 1] + j <= 40000) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][arr[i - 1] + j]);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int v = Integer.parseInt(st.nextToken());
            if (dp[n][v] != 0) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }

        System.out.println(sb.toString());
    }

}
