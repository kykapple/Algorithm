import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Pair> list;
    static PriorityQueue<Integer> pq;

    static class Pair implements Comparable<Pair> {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Pair p) {
            return start - p.start;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        pq = new PriorityQueue<>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Pair(start, end));
        }

        Collections.sort(list);

        int ans = 0;
        for(int i=0; i<n; i++) {
            Pair p = list.get(i);

            if (!pq.isEmpty()) {
                if (pq.peek() <= p.start) {
                    pq.poll();
                }
            }
            pq.add(p.end);

            ans = Math.max(ans, pq.size());
        }

        bw.write(ans + "\n");
        bw.close();
        br.close();
    }
}