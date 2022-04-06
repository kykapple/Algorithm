import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int i = 1; i < m - 1; i++) {
            int currHeight = arr[i];
            int leftHeight = currHeight, rightHeight = currHeight;

            for (int j = i - 1; j >= 0; j--) {
                if (leftHeight < arr[j]) {
                    leftHeight = arr[j];
                }
            }

            for (int j = i + 1; j < m; j++) {
                if (rightHeight < arr[j]) {
                    rightHeight = arr[j];
                }
            }

            ans += Math.min(leftHeight - currHeight, rightHeight - currHeight);
        }

        System.out.println(ans);
    }

}
