import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer;
    static char[][] board;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        solve(0, 0);
        System.out.println(answer);
    }

    static void solve(int idx, int sum) {
        answer = Math.max(answer, sum);
        int x = idx / m;
        int y = idx % m;

        if (x < 0 || x >= n || y < 0 || y >= m) return;

        if (visited[x][y] != 0) {
            solve(idx + 1, sum);
            return;
        }
        visited[x][y] = 1;

        solve(idx + 1, sum + (board[x][y] - '0'));

        for (int i = 1; i < m - y; i++) {
            boolean flag = true;
            StringBuilder sb = new StringBuilder("" + board[x][y]);

            for (int j = 1; j <= i; j++) {
                if (visited[x][y + j] != 0) {
                    flag = false;

                    for (int k = 1; k < j; k++) {
                        visited[x][y + k] -= 1;
                    }

                    break;
                }
                visited[x][y + j] += 1;
                sb.append(board[x][y + j]);
            }

            if (flag) {
                solve(idx + i, sum + Integer.parseInt(sb.toString()));
                for (int j = 1; j <= i; j++) {
                    visited[x][y + j] -= 1;
                }
            }

        }

        for (int i = 1; i < n - x; i++) {
            boolean flag = true;
            StringBuilder sb = new StringBuilder("" + board[x][y]);

            for (int j = 1; j <= i; j++) {
                if (visited[x + j][y] != 0) {
                    flag = false;

                    for (int k = 1; k < j; k++) {
                        visited[x + k][y] -= 1;
                    }

                    break;
                }
                visited[x + j][y] += 1;
                sb.append(board[x + j][y]);
            }

            if (flag) {
                solve(idx + 1, sum + Integer.parseInt(sb.toString()));
                for (int j = 1; j <= i; j++) {
                    visited[x + j][y] -= 1;
                }
            }

        }

        visited[x][y] = 0;
    }

}
