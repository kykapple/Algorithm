import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        int[] prefixSum = new int[n + 1];
        int[][] dp = new int[4][n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 3; i++) {

            for (int j = i * m; j <= n; j++) {

                dp[i][j] = Math.max(
                        dp[i][j - 1],
                        dp[i - 1][j - m] + prefixSum[j] - prefixSum[j - m]
                );

            }

        }

        System.out.println(dp[3][n]);

    }

}
