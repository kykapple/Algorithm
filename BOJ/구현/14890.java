import java.util.*;
import java.io.*;

public class Main {
    static int n, l, answer;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행 단위 접근
        for(int i=0; i<n; i++) {
            if(solve(i, 0))
                answer++;

            if(solve(i, 1))
                answer++;
        }

        System.out.println(answer);

    }

    static boolean solve(int row_col, int flag) {
        boolean[] visited = new boolean[n];
        int[] road = new int[n];

        for(int i=0; i<n; i++) {
            if(flag == 0)
                road[i] = map[row_col][i];
            else
                road[i] = map[i][row_col];
        }

        int cur = road[0];
        for(int i=1; i<n; i++) {
            if(cur + 1 < road[i] || cur - 1 > road[i])
                return false;

            if(cur < road[i]) {
                cur = road[i-1];
                for(int j=i-1; j>i-1-l; j--) {
                    if(j < 0 || j >= n || visited[j] || cur != road[j]) return false;
                    visited[j] = true;
                }
                cur = road[i];
            } else if(cur > road[i]){
                cur = road[i];
                for(int j=i; j<i+l; j++) {
                    if(j < 0 || j >= n || visited[j] || cur != road[j]) return false;
                    visited[j] = true;
                }
            }

        }

        return true;
    }
}