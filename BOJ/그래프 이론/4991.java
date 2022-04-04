import java.io.*;
import java.util.*;

public class Main {
    static int n, m, ans = Integer.MAX_VALUE;
    static ArrayList<Pair> list;
    static boolean[] visited;
    static char[][] board;
    static int[][] dist;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };

    static class Pair {
        int x;
        int y;
        int cnt;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;

            if (m == 0 && n == 0) break;

            list = new ArrayList<>();
            board = new char[n][m];

            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < m; j++) {
                    char ch = str.charAt(j);
                    board[i][j] = ch;
                    if (ch == 'o') {
                        list.add(new Pair(i, j));
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == '*') {
                        list.add(new Pair(i, j));
                    }
                }
            }

            if (!isReachable(list.get(0).x, list.get(0).y)) {
                System.out.println(-1);
                continue;
            }

            visited = new boolean[list.size()];
            dist = new int[list.size()][list.size()];

            for (int i = 0; i < list.size(); i++) {
                Pair p = list.get(i);
                for (int j = 0; j < list.size(); j++) {
                    if (i == j) continue;
                    dist[i][j] = calcDist(p, list.get(j));
                }
            }

            solve(0, 0, 0);

            System.out.println(ans);
        }
    }

    static void solve(int prev, int sum, int cnt) {
        if (sum >= ans) return;
        if (cnt == list.size() - 1) {
            ans = sum;
            return;
        }

        for (int i = 1; i < list.size(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            solve(i, sum + dist[prev][i], cnt + 1);
            visited[i] = false;
        }

    }

    static int calcDist(Pair src, Pair des) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] check = new boolean[n][m];
        check[src.x][src.y] = true;
        q.add(new Pair(src.x, src.y, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();

            if (p.x == des.x && p.y == des.y) {
                return p.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (check[nx][ny] || board[nx][ny] == 'x') continue;

                check[nx][ny] = true;
                q.add(new Pair(nx, ny, p.cnt + 1));
            }
        }

        return -1;
    }

    static boolean isReachable(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        char[][] temp = new char[n][m];
        boolean[][] check = new boolean[n][m];

        check[x][y] = true;
        q.add(new Pair(x, y, 0));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = board[i][j];
            }
        }

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (check[nx][ny] || temp[nx][ny] == 'x') continue;

                if (temp[nx][ny] == '*') temp[nx][ny] = '.';
                check[nx][ny] = true;
                q.add(new Pair(nx, ny, p.cnt + 1));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == '*') return false;
            }
        }

        return true;
    }

}
