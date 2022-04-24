import java.io.*;
import java.util.*;

public class Main {
    static int d, p;
    static int[][] dp;
    static ArrayList<Pair> list;

    static class Pair {
        int length;
        int capacity;

        public Pair(int length, int capacity) {
            this.length = length;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        d = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        dp = new int[p][d + 1];
        list = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

            Arrays.fill(dp[i], -1);
        }

        list.sort((p1, p2) -> p2.capacity - p1.capacity);

        System.out.println(solve(0, 0, 0));
    }

    static int solve(int idx, int currLength, int maxCapacity) {
        if (currLength == d) {
            return maxCapacity;
        }
        if (idx >= list.size() || currLength > d) {
            return 0;
        }

        if (dp[idx][currLength] != -1) return dp[idx][currLength];
        dp[idx][currLength] = 0;

        Pair p = list.get(idx);
        dp[idx][currLength] = Math.max(
                solve(idx + 1, currLength, maxCapacity),
                solve(idx + 1, currLength + p.length, maxCapacity != 0 ? Math.min(maxCapacity, p.capacity) : p.capacity)
        );

        return dp[idx][currLength];
    }

}
