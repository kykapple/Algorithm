import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer;
    static char[][] board;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };

    static class Pair {
        int x;
        int y;
        int visited;

        public Pair(int x, int y, int visited) {
            this.x = x;
            this.y = y;
            this.visited = visited;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        solve(0, 0, (1 << (board[0][0] - 'A')), 1);
        System.out.println(answer);
    }

    static void solve(int x, int y, int visited, int cnt) {
        answer = Math.max(answer, cnt);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if ((visited & (1 << (board[nx][ny] - 'A'))) != 0) continue;

            solve(nx, ny, visited | (1 << (board[nx][ny] - 'A')), cnt + 1);
        }
    }

}

