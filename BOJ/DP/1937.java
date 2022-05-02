import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board, dp;
    static boolean[][] visited;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        dp = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }

        System.out.println(answer);
    }

    static int dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (board[x][y] >= board[nx][ny]) continue;

            if (!visited[nx][ny]) {
                dp[nx][ny] = dfs(nx, ny);
            }
            dp[x][y] = Math.max(dp[x][y], dp[nx][ny]);
        }

        return dp[x][y] += 1;
    }

}
