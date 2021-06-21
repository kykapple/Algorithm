import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k, ans = 987654321;
    static int graph[][];
    static Info arr[];
    static boolean check[];

    static class Info {
        int r;
        int c;
        int s;

        public Info(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[n+1][m+1];
        arr = new Info[k];
        check = new boolean[k];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            arr[i] = new Info(r,c,s);
        }

        solve(0, graph, -1);

        bw.write(ans + "\n");
        br.close();
        bw.close();
    }

    static void solve(int cnt, int[][] map, int idx) {
        int[][] temp = new int[n+1][m+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                temp[i][j] = map[i][j];
            }
        }

        if(idx != -1) {
            Info info = arr[idx];
            int start_x = info.r-info.s, start_y = info.c-info.s, end_x = info.r+info.s, end_y = info.c+info.s;
            move(temp, start_x, start_y, end_x, end_y);
        }

        if(cnt == arr.length) {
            ans = Math.min(ans, getValue(temp));
            return;
        }

        for(int i=0; i<arr.length; i++) {
            if(!check[i]) {
                check[i] = true;
                solve(cnt+1, temp, i);
                check[i] = false;
            }
        }
    }

    static int getValue(int[][] map) {
        int res = 987654321;

        for(int i=1; i<=n; i++) {
            int temp = 0;

            for(int j=1; j<=m; j++) {
                temp += map[i][j];
            }

            res = Math.min(res, temp);
        }

        return res;
    }

    static void move(int[][] map, int start_x, int start_y, int end_x, int end_y) {
        if(start_x == end_x && start_y == end_y) return;

        // 오른쪽으로 이동
        int temp_val1 = 0, temp_val2 = map[start_x][start_y];
        for(int i=start_y+1; i<=end_y; i++) {
            temp_val1 = map[start_x][i];
            map[start_x][i] = temp_val2;
            temp_val2 = temp_val1;
        }

        // 아래로 이동
        for(int i=start_x+1; i<=end_x; i++) {
            temp_val1 = map[i][end_y];
            map[i][end_y] = temp_val2;
            temp_val2 = temp_val1;
        }

        // 왼쪽으로 이동
        for(int i=end_y-1; i>=start_y; i--) {
            temp_val1 = map[end_x][i];
            map[end_x][i] = temp_val2;
            temp_val2 = temp_val1;
        }

        // 위쪽으로 이동
        for(int i=end_x-1; i>=start_x; i--) {
            temp_val1 = map[i][start_y];
            map[i][start_y] = temp_val2;
            temp_val2 = temp_val1;
        }

        move(map, start_x+1, start_y+1, end_x-1, end_y-1);
    }
}