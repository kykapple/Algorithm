import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] board;
    static int[][] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        prefixSum = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] + board[i - 1][j - 1] - prefixSum[i - 1][j - 1];
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                for (int k = i; k < n; k++) {
                    for (int t = j; t < m; t++) {
                        int sum = prefixSum[k + 1][t + 1] - (prefixSum[i][t + 1] + prefixSum[k + 1][j]) + prefixSum[i][j];
                        ans = Math.max(ans, sum);
                    }
                }

            }
        }

        System.out.println(ans);
    }

}

