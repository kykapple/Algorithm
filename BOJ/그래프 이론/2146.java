import java.io.*;
import java.util.*;

public class Main {
    static int n, answer = Integer.MAX_VALUE;
    static boolean[] area;
    static int[][] board, map;
    static boolean[][] visited;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };

    static class Pair {
        int x;
        int y;
        int dist;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        map = new int[n][n];
        visited= new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && board[i][j] != 0) {
                    bfs(i, j, num);
                    num += 1;
                }
            }
        }

        area = new boolean[num];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!area[map[i][j]] && map[i][j] != 0) {
                    area[map[i][j]] = true;
                    answer = Math.min(answer, calcDist(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    static int calcDist(int x, int y) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p1.dist - p2.dist);
        visited = new boolean[n][n];

        pq.add(new Pair(x, y, 0));
        visited[x][y] = true;

        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if (map[p.x][p.y] != 0 && map[p.x][p.y] != map[x][y]) {
                return p.dist - 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                if (map[nx][ny] == map[x][y]) {
                    pq.add(new Pair(nx, ny, p.dist));
                } else {
                    pq.add(new Pair(nx, ny, p.dist + 1));
                }
            }
        }

        return -1;
    }

    static void bfs(int x, int y, int num) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));

        visited[x][y] = true;
        map[x][y] = num;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[nx][ny] || board[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                map[nx][ny] = num;
                q.add(new Pair(nx, ny));
            }
        }
    }


}
