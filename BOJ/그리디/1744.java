import java.io.*;
import java.util.*;

public class Main {
    static int n, k, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> neg = new PriorityQueue<>();
        PriorityQueue<Integer> pos = new PriorityQueue<>((a, b) -> b - a);

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());

            if(v <= 0) {
                neg.add(v);
            } else {
                pos.add(v);
            }
        }

        while(!neg.isEmpty()) {
            int v = neg.poll();
            k = 1;

            if(!neg.isEmpty()) {
                k = neg.poll();
            }

            ans += (v * k);
        }

        while(!pos.isEmpty()) {
            int v = pos.poll();
            boolean flag = false;

            if(!pos.isEmpty()) {
                flag = true;
                k = pos.poll();
            }

            if(flag) {
                if(v == 1 || k == 1) {
                    ans += (v + k);
                } else {
                    ans += (v * k);
                }
            } else {
                ans += v;
            }
        }

        System.out.println(ans);

    }

}
