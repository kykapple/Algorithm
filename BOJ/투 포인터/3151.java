import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int arr[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        long ans = 0;
        for(int i=0; i<n; i++) {
            int cur = arr[i];

            int left = i+1, right = n-1;
            while(left < right) {
                int temp = cur + arr[left] + arr[right];

                if(temp == 0) {
                    if(arr[left] == arr[right]) {
                        long cnt = right - left + 1;
                        ans += ((cnt * (cnt -1)) / 2);
                        break;
                    } else {
                        int left_val = arr[left];
                        long left_cnt = 0;
                        while(arr[left] == left_val) {
                            left_cnt++;
                            left++;
                        }

                        int right_val = arr[right];
                        long right_cnt = 0;
                        while(arr[right] == right_val) {
                            right_cnt++;
                            right--;
                        }

                        ans += (left_cnt * right_cnt);
                    }
                } else if(temp < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        bw.write(ans + "\n");
        br.close();
        bw.close();
    }
}