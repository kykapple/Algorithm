import java.io.*;
import java.util.*;

public class Main {
    static int n, m, fuel, taxi_x, taxi_y;
    static int graph[][], src[][];
    static boolean check[][][][];
    static int dx[] = {-1,1,0,0}, dy[] = {0,0,-1,1};

    static class Pair {
        int x;
        int y;
        int cost;
        boolean success;

        public Pair(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public Pair(int x, int y, int cost, boolean success) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.success = success;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        graph = new int[n+1][n+1];
        src = new int[n+1][n+1];
        check = new boolean[n+1][n+1][n+1][n+1];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi_x = Integer.parseInt(st.nextToken());
        taxi_y = Integer.parseInt(st.nextToken());

        for(int i=2; i<m+2; i++) {
            st = new StringTokenizer(br.readLine());

            int start_x = Integer.parseInt(st.nextToken());
            int start_y = Integer.parseInt(st.nextToken());
            int end_x = Integer.parseInt(st.nextToken());
            int end_y = Integer.parseInt(st.nextToken());

            src[start_x][start_y] = i;
            check[start_x][start_y][end_x][end_y] = true;
        }

        boolean flag = true;
        while(m > 0) {
            Pair src = find_guest(taxi_x, taxi_y);
            if(src == null) {
                flag = false;
                break;
            }

            fuel -= src.cost;
            if(fuel <= 0) {
                flag = false;
                break;
            }

            Pair des = drive(src.x, src.y);
            if(des == null) {
                flag = false;
                break;
            } else {
                if(des.cost > fuel) {
                    flag = false;
                    break;
                } else {
                    fuel += des.cost;
                }
            }

            taxi_x = des.x;
            taxi_y = des.y;
            m--;
        }

        if(flag)
            bw.write(fuel + "\n");
        else
            bw.write(-1 + "\n");

        br.close();
        bw.close();
    }

    static Pair find_guest(int cur_x, int cur_y) {
        if(graph[cur_x][cur_y] != 0 && graph[cur_x][cur_y] != 1)
            return new Pair(cur_x, cur_y, 0);

        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][n+1];

        q.add(new Pair(cur_x, cur_y, 0));
        visited[cur_x][cur_y] = true;

        Pair ans = null;
        while(!q.isEmpty()) {
            Pair p = q.poll();

            if(src[p.x][p.y] != 0) {
                if(ans == null)
                    ans = new Pair(p.x, p.y, p.cost);
                else {
                    if(ans.cost < p.cost) continue;

                    if(p.x == ans.x) {
                        if(p.y < ans.y)
                            ans = p;
                    } else {
                        if(p.x < ans.x)
                            ans = p;
                    }

                    continue;
                }
            }

            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n) continue;
                if(visited[nx][ny] || graph[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                q.add(new Pair(nx, ny, p.cost + 1));
            }
        }

        return ans;
    }

    static Pair drive(int cur_x, int cur_y) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][n+1];

        q.add(new Pair(cur_x, cur_y, 0));
        visited[cur_x][cur_y] = true;

        while(!q.isEmpty()) {
            Pair p = q.poll();

            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n) continue;
                if(visited[nx][ny] || graph[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                if(check[cur_x][cur_y][nx][ny]) {
                    check[cur_x][cur_y][nx][ny] = false;
                    src[cur_x][cur_y] = 0;
                    return new Pair(nx, ny, p.cost+1, true);
                } else {
                    q.add(new Pair(nx, ny, p.cost+1));
                }
            }
        }

        return null;
    }
}

