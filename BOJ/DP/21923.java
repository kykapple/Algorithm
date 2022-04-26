import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] board;
    static long[][] up, down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        up = new long[n][m];
        down = new long[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                up[i][j] = Long.MIN_VALUE;
                down[i][j] = Long.MIN_VALUE;
            }
        }

        flyUp(0, m - 1);
        flyDown(0, 0);

        long answer = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.max(answer, up[i][j] + down[i][j]);
            }
        }

        System.out.println(answer);
    }

    static long flyUp(int x, int y) {
        if (x == n - 1 && y == 0) {
            return up[x][y] = board[x][y];
        }
        if (up[x][y] != Long.MIN_VALUE) return up[x][y];

        if (x < n - 1) {
            up[x][y] = Math.max(up[x][y], flyUp(x + 1, y) + board[x][y]);
        }
        if (y > 0) {
            up[x][y] = Math.max(up[x][y], flyUp(x, y - 1) + board[x][y]);
        }

        return up[x][y];
    }

    static long flyDown(int x, int y) {
        if (x == n - 1 && y == m - 1) {
            return down[x][y] = board[x][y];
        }
        if (down[x][y] != Long.MIN_VALUE) return down[x][y];

        if (x < n - 1) {
            down[x][y] = Math.max(down[x][y], flyDown(x + 1, y) + board[x][y]);
        }
        if (y < m - 1) {
            down[x][y] = Math.max(down[x][y], flyDown(x, y + 1) + board[x][y]);
        }

        return down[x][y];
    }

}
