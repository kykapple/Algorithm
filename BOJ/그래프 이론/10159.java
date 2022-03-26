import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Pair>> list;
    static int[] count;
    static boolean[][] visited;

    static class Pair {
        int next;
        int status;

        public Pair(int next, int status) {
            this.next = next;
            this.status = status;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        count = new int[n + 1];
        visited = new boolean[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
             st = new StringTokenizer(br.readLine());

             int a = Integer.parseInt(st.nextToken());
             int b = Integer.parseInt(st.nextToken());

             list.get(a).add(new Pair(b, 1));
             list.get(b).add(new Pair(a, -1));
        }

        for (int i = 1; i <= n; i++) {
            visited[i][i] = true;
            dfs(i, 0, i);
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(n - (count[i] + 1));
        }

    }

    static void dfs(int curr, int status, int start) {

        for (int i = 0; i < list.get(curr).size(); i++) {
            Pair p = list.get(curr).get(i);

            if ((status == 1 && p.status == -1) || (status == -1 && p.status == 1)) continue;

            if (!visited[start][p.next]) {
                visited[start][p.next] = true;
                count[start] += 1;
                dfs(p.next, p.status, start);
            }
        }

    }

}
