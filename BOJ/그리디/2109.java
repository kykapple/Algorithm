import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] period;
    static PriorityQueue<Pair> pq;

    static class Pair {
        int day;
        int cost;

        public Pair(int day, int cost) {
            this.day = day;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        period = new int[10001];
        pq = new PriorityQueue<>((p1, p2) -> p2.cost - p1.cost);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            pq.add(new Pair(d, p));
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            for (int i = p.day; i >= 1; i--) {
                if (period[i] == 0) {
                    period[i] += 1;
                    answer += p.cost;
                    break;
                }
            }
        }

        System.out.println(answer);
    }

}
