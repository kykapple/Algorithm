import java.io.*;
import java.util.*;

public class Main {
    static int n, ans, sharkSize = 2, cnt;
    static int[][] graph;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };
    static Pair shark;

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
        graph = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());

                if(graph[i][j] == 9) {
                    shark = new Pair(i, j);
                }
            }
        }

        while(true) {
            Pair closestFish = getAvailablePos();
            if(closestFish == null) {
                break;
            }

            graph[shark.x][shark.y] = 0;
            graph[closestFish.x][closestFish.y] = 0;

            cnt++;
            ans += closestFish.dist;
            shark = closestFish;
            if(cnt == sharkSize) {
                sharkSize++;
                cnt = 0;
            }
        }

        System.out.println(ans);

    }

    static Pair getAvailablePos() {
        Pair closestFish = null;
        Queue<Pair> q = new LinkedList<>();
        boolean visited[][] = new boolean[n][n];

        q.add(new Pair(shark.x, shark.y, 0));
        visited[shark.x][shark.y] = true;

        while(!q.isEmpty()) {
            Pair p = q.poll();

            if(graph[p.x][p.y] != 0 && graph[p.x][p.y] < sharkSize) {
                if(closestFish == null || closestFish.dist > p.dist) {
                    closestFish = p;
                } else if(closestFish.dist == p.dist){
                    if(closestFish.x > p.x) {
                        closestFish = p;
                    } else if(closestFish.x == p.x && closestFish.y > p.y) {
                        closestFish = p;
                    }
                }
            }

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(graph[nx][ny] > sharkSize || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new Pair(nx, ny, p.dist + 1));
            }
        }

        return closestFish;
    }

}
