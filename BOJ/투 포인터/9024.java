import java.io.*;
import java.util.*;

public class Main {
    static int t, n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int left = 0, right = n -1;
            int cnt = 0, min = Integer.MAX_VALUE;

            while (left < right) {
                int sum = arr[left] + arr[right];
                int gap = Math.abs(k - (arr[left] + arr[right]));

                if (min > gap) {
                    min = gap;
                    cnt = 1;
                } else if (min == gap) {
                    cnt += 1;
                }

                if (sum > k) {
                    right--;
                } else {
                    left++;
                }
            }

            System.out.println(cnt);
        }
    }

}
