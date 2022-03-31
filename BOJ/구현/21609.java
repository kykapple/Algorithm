import java.io.*;
import java.util.*;

public class Main {
    static int n, m, groupSize, maxGroupSize, rainbowBlockSize, answer;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Pair> q;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
        System.out.println(answer);
    }

    static void solve() {
        while (true) {
            Pair p = findBiggestBlockGroup();

            if (p == null || maxGroupSize < 2) break;

            removeBlockGroup(p.x, p.y, board[p.x][p.y]);
            answer += (maxGroupSize * maxGroupSize);
            handleGravity();
            board = rotate(board);
            handleGravity();
        }
    }

    static int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] new_arr = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                new_arr[n - j - 1][i] = arr[i][j];
            }
        }

        return new_arr;
    }

    static void handleGravity() {
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int curr = board[i][j];
                if (curr == -2 || curr == -1) continue;

                int k = i;
                while (k + 1 < n && board[k + 1][j] == -2) {
                    k += 1;
                }

                board[i][j] = -2;
                board[k][j] = curr;
            }
        }
    }

    static void removeBlockGroup(int x, int y, int color) {
        board[x][y] = -2;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (board[nx][ny] == -1 || board[nx][ny] == -2) continue;

            if (board[nx][ny] == color || board[nx][ny] == 0) {
                removeBlockGroup(nx, ny, color);
            }
        }

    }

    static Pair findBiggestBlockGroup() {
        visited = new boolean[n][n];
        int maxRainbowBlock = 0;
        maxGroupSize = 0;
        Pair p = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1 || board[i][j] == -2 || visited[i][j] || board[i][j] == 0) continue;

                groupSize = 0;
                rainbowBlockSize = 0;
                dfs(i, j, board[i][j]);
                if (maxGroupSize <= groupSize) {
                    if (maxGroupSize == groupSize && maxRainbowBlock > rainbowBlockSize) {
                        continue;
                    }

                    p = new Pair(i, j);
                    maxGroupSize = groupSize;
                    maxRainbowBlock = rainbowBlockSize;
                }

                while (!q.isEmpty()) {
                    Pair pair = q.poll();
                    visited[pair.x][pair.y] = false;
                }
            }
        }

        return p;
    }

    static void dfs(int x, int y, int color) {
        visited[x][y] = true;
        groupSize += 1;
        if (board[x][y] == 0) {
            rainbowBlockSize += 1;
            q.add(new Pair(x, y));
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (visited[nx][ny] || board[nx][ny] == -1 || board[nx][ny] == -2) continue;

            if (board[nx][ny] == color || board[nx][ny] == 0) {
                dfs(nx, ny, color);
            }
        }
    }

}
