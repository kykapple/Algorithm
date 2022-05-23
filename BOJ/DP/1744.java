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
        dp = new int[2][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < 2; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(0, 0));
    }

    static int solve(int isOpen, int curr) {
        if (curr == n) {
            if (isOpen == 1) return arr[curr - 1];
            return 0;
        }

        if (dp[isOpen][curr] != -1) return dp[isOpen][curr];
        dp[isOpen][curr] = 0;

        if (isOpen == 0) {
            dp[isOpen][curr] = Math.max(solve(0, curr + 1) + arr[curr], solve(1, curr + 1));
        } else {
            dp[isOpen][curr] = solve(0, curr + 1) + (arr[curr - 1] * arr[curr]);
        }

        return dp[isOpen][curr];
    }

}

