import java.io.*;
import java.util.*;

public class Main {
    static int v, e, k;
    static int dist[];
    static ArrayList<Pair> list[];

    static class Pair {
        int point;
        int weight;

        public Pair(int point, int weight) {
            this.point = point;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        dist = new int[v + 1];
        list = new ArrayList[v + 1];
        for(int i = 1; i <= v; i++) {
            dist[i] = Integer.MAX_VALUE;
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[from].add(new Pair(to, weight));
        }

        dijkstra(k);

        for (int i = 1; i <= v; i++) {
            if(dist[i] == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(dist[i]);
        }

    }

    static void dijkstra(int start) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Pair(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Pair p = pq.poll();

            if(dist[p.point] < p.weight) continue;

            for (int i = 0; i < list[p.point].size(); i++) {
                Pair q = list[p.point].get(i);

                if(dist[q.point] > p.weight + q.weight) {
                    dist[q.point] = p.weight + q.weight;
                    pq.add(new Pair(q.point, dist[q.point]));
                }
            }
        }

    }
}
