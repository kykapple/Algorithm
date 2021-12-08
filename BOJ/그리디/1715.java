import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        while(!pq.isEmpty()) {
            int a = pq.poll();

            if(!pq.isEmpty()) {
                int b = pq.poll();
                ans += (a + b);
                pq.add(a + b);
            }
        }

        System.out.println(ans);

    }

}
