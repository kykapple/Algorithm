import java.io.*;
import java.util.*;

public class Main {
    static int n, m, ans, lastDist;
    static ArrayList<Pair> list = new ArrayList<>();
    static int parent[];

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int dist;

        public Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair p) {
            return this.dist - p.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new Pair(a, b, c));
        }

        Collections.sort(list);

        for(int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < list.size(); i++) {
            Pair p = list.get(i);

            if(getParent(p.x) == getParent(p.y)) continue;

            union(p.x, p.y);
            ans += p.dist;
            lastDist = p.dist;
        }

        System.out.println(ans - lastDist);
    }

    static int getParent(int v) {
        if(parent[v] == v) return parent[v];
        return parent[v] = getParent(parent[v]);
    }

    static void union(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

}
