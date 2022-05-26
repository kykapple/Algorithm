import java.io.*;
import java.util.*;

public class Main {
    static int n, answer, INF = 987654321;
    static int[][] graph;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        check = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = true;
        loop:
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (k == i || k == j) continue;
                    if (graph[i][j] == graph[i][k] + graph[k][j]) {
                        check[i][j] = true;
                        check[j][i] = true;
                    }
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        flag = false;
                        break loop;
                    }
                }
            }
        }

        if (!flag) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!check[i][j]) {
                    answer += graph[i][j];
                }
            }
        }

        System.out.println(answer);
    }

}
