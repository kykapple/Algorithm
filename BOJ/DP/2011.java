import java.io.*;
import java.util.*;

public class Main {
    static char[] password;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        password = br.readLine().toCharArray();
        dp = new int[password.length][27];

        for (int i = 0; i < password.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(1, "" + password[0]));
    }

    static int solve(int idx, String curr) {
        int v = Integer.parseInt(curr);
        if (v < 1 || v > 26) {
            return 0;
        }
        if (idx == password.length) {
            return 1;
        }

        if (dp[idx][v] != -1) {
            return dp[idx][v];
        }
        dp[idx][v] = 0;

        dp[idx][v] += solve(idx + 1, "" + password[idx]) % 1000000;
        dp[idx][v] += solve(idx + 1, curr + password[idx]) % 1000000;

        return dp[idx][v] % 1000000;
    }


}
