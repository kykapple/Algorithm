import java.io.*;
import java.util.*;

public class Main {
    static int n, m, h;
    static int[][] dp;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        dp = new int[n][h + 1];
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new ArrayList<>());

            while (st.hasMoreTokens()) {
                list.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < n; i++) {
            list.get(i).add(0);
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(0, 0));
    }

    static int solve(int idx, int height) {
        if (height > h) return 0;
        if (idx == n) {
            if (height == h) return 1;
            return 0;
        }
        if (dp[idx][height] != -1) return dp[idx][height];
        dp[idx][height] = 0;

        for (int i = 0; i < list.get(idx).size(); i++) {
            int h = list.get(idx).get(i);
            dp[idx][height] += solve(idx + 1, height + h) % 10007;
        }

        return dp[idx][height] % 10007;
    }

}
