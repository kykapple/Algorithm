import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int arr[];
    static int dp[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        dp = new int[n][n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                dp[i][j] = -1;

        bw.write(solve(0, n-1) + "\n");

        br.close();
        bw.close();
    }

    static int solve(int left, int right) {
        if(left >= right) return 0;
        if(dp[left][right] != -1) return dp[left][right];

        if(arr[left] == arr[right]) {
            dp[left][right] = solve(left+1, right-1);
        } else {
            dp[left][right] = Math.min(solve(left+1, right), solve(left, right-1)) + 1;
        }

        return dp[left][right];
    }
}
