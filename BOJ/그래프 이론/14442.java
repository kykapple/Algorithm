import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int n, m, k, ans = 987654321;
    static int graph[][];
    static boolean visited[][][];
    static int dx[] = {-1,1,0,0}, dy[] = {0,0,-1,1};

    static class Pair {
        int x;
        int y;
        int cost;
        int power;

        public Pair(int x, int y, int cost, int power) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.power = power;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new boolean[k+1][n][m];

        for(int i=0; i<n; i++) {
            String str = br.readLine();
            for(int j=0; j<m; j++) {
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        BFS();
        if(ans == 987654321)
            bw.write(-1 + "\n");
        else
            bw.write((ans+1) + "\n");

        br.close();
        bw.close();
    }

    static void BFS() {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0, 0, k));
        visited[k][0][0] = true;

        while(!q.isEmpty()) {
            Pair p = q.poll();

            if(p.x == n-1 && p.y == m-1)
                ans = Math.min(ans, p.cost);

            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(graph[nx][ny] == 0 && !visited[p.power][nx][ny]) {
                    visited[p.power][nx][ny] = true;
                    q.add(new Pair(nx, ny, p.cost + 1, p.power));
                } else {
                    if(p.power > 0 && !visited[p.power - 1][nx][ny]) {
                        visited[p.power - 1][nx][ny] = true;
                        q.add(new Pair(nx, ny, p.cost + 1, p.power - 1));
                    }
                }
            }
        }
    }
}