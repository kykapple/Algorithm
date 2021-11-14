import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int graph[][];
    static boolean visited[][][];
    static int dx[] = {0, -1, 0 , 1}, dy[] = {-1, 0, 1, 0};

    static class Coordinate {
        int x;
        int y;
        int dist;
        int ability;

        public Coordinate(int x, int y, int dist, int ability) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.ability = ability;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new boolean[2][n][m];

        for(int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for(int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(str[j]);
            }
        }

        System.out.println(BFS());

    }

    static int BFS() {
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(0, 0, 1, 1));
        visited[1][0][0] = true;

        while(!q.isEmpty()) {
            Coordinate c = q.poll();

            if(c.x == n - 1 && c.y == m - 1) {
                return c.dist;
            }

            for(int i = 0; i < 4; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(graph[nx][ny] == 1 && c.ability == 1 && !visited[0][nx][ny]) {
                    visited[0][nx][ny] = true;
                    q.add(new Coordinate(nx, ny, c.dist + 1, 0));
                } else if(graph[nx][ny] == 0 && !visited[c.ability][nx][ny]){
                    visited[c.ability][nx][ny] = true;
                    q.add(new Coordinate(nx, ny, c.dist + 1, c.ability));
                }
            }
        }

        return -1;

    }

}
