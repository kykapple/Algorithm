import java.io.*;
import java.util.*;

public class Main {
    static int n, m, x, INF = 987654321;
    static int[] dist, reverseDist;
    static ArrayList<ArrayList<Pair>> list, reverseList;

    static class Pair {
        int x;
        int dist;

        public Pair(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        reverseDist = new int[n + 1];
        list = new ArrayList<>();
        reverseList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Pair(b, c));
            reverseList.get(b).add(new Pair(a, c));
        }

        dijkstra(x, dist, list);
        dijkstra(x, reverseDist, reverseList);

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, dist[i] + reverseDist[i]);
        }

        System.out.println(answer);
    }

    static void dijkstra(int start, int[] dist, ArrayList<ArrayList<Pair>> graph) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p1.dist - p2.dist);
        Arrays.fill(dist, INF);

        pq.add(new Pair(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if (dist[p.x] < p.dist) continue;

            for (int i = 0; i < graph.get(p.x).size(); i++) {
                Pair q = graph.get(p.x).get(i);
                if (dist[q.x] > dist[p.x] + q.dist) {
                    dist[q.x] = dist[p.x] + q.dist;
                    pq.add(new Pair(q.x, dist[q.x]));
                }
            }
        }
    }

}
