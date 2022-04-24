import java.io.*;
import java.util.*;

public class Main {
    static int t, n, m;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        dp = new long[11][2001];

        for (int i = 0 ; i <= 10; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            long answer = 0;
            for (int j = m; j >= 1; j--) {
                answer += solve(n - 1, j);
            }

            System.out.println(answer);
        }
    }

    static long solve(int cnt, int curr) {
        if (curr / (int) Math.pow(2, cnt) < 1) {
            return 0;
        }
        if (cnt == 0) {
            return 1;
        }

        if (dp[cnt][curr] != -1) return dp[cnt][curr];
        dp[cnt][curr] = 0;

        int half = (int) Math.ceil((double)curr / 2);
        for (int i = half; i < curr; i++) {
            dp[cnt][curr] += solve(cnt - 1, curr - i);
        }

        return dp[cnt][curr];
    }

}
