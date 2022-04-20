import java.io.*;
import java.util.*;

public class Main {
    static int n, k, left, right, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right += arr[i];
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = 0, cnt = 0;

            for (int i = 0; i < n; i++) {
                sum += arr[i];
                if (sum >= mid) {
                    cnt += 1;
                    sum = 0;
                }
            }

            if (cnt >= k) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }


}
