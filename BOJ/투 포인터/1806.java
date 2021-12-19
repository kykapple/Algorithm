import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int right = 0, sum = arr[0], ans = Integer.MAX_VALUE;
        for(int left = 0; left < n; left++) {
            while(sum < s && right < n - 1) {
                right++;
                sum += arr[right];
            }

            if(sum >= s) {
                ans = Math.min(ans, right - left + 1);
            }
            sum -= arr[left];
        }

        if(ans == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(ans);
        }

    }

}
