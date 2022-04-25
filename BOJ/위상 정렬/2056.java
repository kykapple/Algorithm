import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] entry, times, dp;
    static ArrayList<ArrayList<Integer>> graph;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        entry = new int[n + 1];
        times = new int[n + 1];
        dp = new int[n + 1];
        graph = new ArrayList<>();
        q = new LinkedList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());

            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int child = Integer.parseInt(st.nextToken());
                entry[i] += 1;
                graph.get(child).add(i);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (entry[i] == 0) {
                q.add(i);
                dp[i] = times[i];
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();

            for (int i = 0; i < graph.get(x).size(); i++) {
                int y = graph.get(x).get(i);
                dp[y] = Math.max(dp[y], dp[x] + times[y]);

                if (--entry[y] == 0) {
                    q.add(y);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }

}
