import java.io.*;
import java.util.*;

public class Main {
    static int n, m, tc = 1;
    static boolean isTree;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            int cnt = 0;
            visited = new boolean[n + 1];
            graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    isTree = true;
                    dfs(-1, i);

                    if (isTree) cnt += 1;
                }
            }

            if (cnt > 1) System.out.printf("Case %d: A forest of %d trees.\n", tc, cnt);
            else if (cnt == 1) System.out.printf("Case %d: There is one tree.\n", tc);
            else System.out.printf("Case %d: No trees.\n", tc);

            tc += 1;
        }

    }

    static void dfs(int prev, int curr) {
        visited[curr] = true;

        for (int i = 0; i < graph.get(curr).size(); i++) {
            int next = graph.get(curr).get(i);
            if (visited[next] && next != prev) {
                isTree = false;
                continue;
            }

            if (!visited[next]) {
                dfs(curr, next);
            }
        }
    }

}

