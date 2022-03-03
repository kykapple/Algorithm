import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;
    static int[][] dp;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        dp = new int[n][n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            bw.write(isPalindrome(s, e) + "\n");
        }
        bw.close();
    }

    static int isPalindrome(int start, int end) {
        if (start == end || start > end) return 1;
        if (arr[start] != arr[end]) return 0;

        if (dp[start][end] != -1) return dp[start][end];

        dp[start][end] = isPalindrome(start + 1, end - 1);

        return dp[start][end];
    }

}
