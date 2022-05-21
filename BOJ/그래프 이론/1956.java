import java.io.*;
import java.util.*;

public class Main {
    static int v, e, INF = 987654321;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        graph = new int[v + 1][v + 1];

        for (int i = 0; i <= v; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
        }

        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int answer = INF;
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j) continue;
                answer = Math.min(answer, graph[i][j] + graph[j][i]);
            }
        }

        if (answer == INF) System.out.println(-1);
        else System.out.println(answer);
    }
}
