import java.io.*;
import java.util.*;

public class Main {
    static char[][] board;
    static Queue<Pair> q;
    static int[] dx = { -1, -1, -1, 0, 0, 0, 1, 1, 1 }, dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[8][8];
        q = new LinkedList<>();

        for (int i = 0; i < 8; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        q.add(new Pair(7, 0));

        if (solve()) System.out.println(1);
        else System.out.println(0);
    }

    static boolean solve() {
        while (true) {
            if (bfs()) return true;
            if (q.isEmpty()) break;
            moveWall();
        }

        return false;
    }

    static boolean bfs() {
        int size = q.size();
        boolean[][] visited = new boolean[8][8];

        for (int t = 0; t < size; t++) {
            Pair p = q.poll();

            if (board[p.x][p.y] == '#') continue;

            for (int i = 0; i < 9; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) continue;
                if (board[nx][ny] == '#' || visited[nx][ny]) continue;

                if (nx == 0 && ny == 7) return true;

                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
            }
        }

        return false;
    }

    static void moveWall() {
        for (int i = 0; i < 8; i++) {
            board[7][i] = '.';
        }

        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == '.') continue;
                board[i][j] = '.';
                board[i + 1][j] = '#';
            }
        }
    }

}
