import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[][] visited;
    static int[][] isLightUp;
    static Map<Pair, ArrayList<Pair>> keys;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int w;

        public Pair(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Pair o) {
            if (o.w == w) return isLightUp[o.x][o.y] - isLightUp[x][y];
            return o.w - w;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        isLightUp = new int[n][n];
        visited = new boolean[n][n];
        keys = new HashMap<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            Pair src = new Pair(x, y, 0);
            Pair des = new Pair(a, b, 0);
            if (!keys.containsKey(src)) {
                keys.put(src, new ArrayList<>());
            }
            keys.get(src).add(des);
        }

        System.out.println(solve());
    }

    static int solve() {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0, 0));
        isLightUp[0][0] = 1;
        visited[0][0] = true;

        int cnt = 1;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if (p.w == -1 && isLightUp[p.x][p.y] == 0) continue;

            if (keys.containsKey(p)) {
                ArrayList<Pair> list = keys.get(p);
                for (Pair r : list) {
                    if (isLightUp[r.x][r.y] == 0) cnt++;
                    isLightUp[r.x][r.y] = 1;
                }
                PriorityQueue<Pair> tmp = new PriorityQueue<>();
                while (!pq.isEmpty()) {
                    tmp.add(pq.poll());
                }
                pq = tmp;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                if (isLightUp[nx][ny] == 0) {
                    pq.add(new Pair(nx, ny, -1));
                } else {
                    pq.add(new Pair(nx, ny, 0));
                }
            }
        }

        return cnt;
    }

}
