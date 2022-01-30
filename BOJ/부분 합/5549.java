import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static char[][] graph;
    static Pair[][] sum;

    static class Pair {
        int jungle;
        int sea;
        int ice;

        public Pair(int jungle, int sea, int ice) {
            this.jungle = jungle;
            this.sea = sea;
            this.ice = ice;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n + 1][m + 1];
        sum = new Pair[n + 1][m + 1];

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= m; i++) {
            sum[0][i] = new Pair(0, 0, 0);
        }

        for (int i = 0; i <= n; i++) {
            sum[i][0] = new Pair(0, 0, 0);
        }

        for (int i = 1; i <= n; i++) {
            String str = br.readLine();

            for (int j = 1; j <= m; j++) {
                graph[i][j] = str.charAt(j - 1);

                Pair up = sum[i - 1][j];
                Pair left = sum[i][j - 1];
                Pair diagonal = sum[i - 1][j - 1];

                int jungle = up.jungle + left.jungle - diagonal.jungle;
                int sea = up.sea + left.sea - diagonal.sea;
                int ice = up.ice + left.ice - diagonal.ice;

                sum[i][j] = new Pair(jungle, sea, ice);

                if (graph[i][j] == 'J') {
                    sum[i][j].jungle += 1;
                } else if (graph[i][j] == 'I') {
                    sum[i][j].ice += 1;
                } else {
                    sum[i][j].sea += 1;
                }
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            Pair p = sum[c][d];
            Pair up = sum[a - 1][d];
            Pair left = sum[c][b - 1];
            Pair diagonal = sum[a - 1][b - 1];

            int jungle = p.jungle - up.jungle - left.jungle + diagonal.jungle;
            int sea = p.sea - up.sea - left.sea + diagonal.sea;
            int ice = p.ice - up.ice - left.ice + diagonal.ice;
            System.out.println(jungle + " " + sea + " " + ice);
        }

    }

}
