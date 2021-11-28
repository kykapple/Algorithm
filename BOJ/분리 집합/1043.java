import java.io.*;
import java.util.*;

public class Main {
    static int n, m, ans;
    static int[] parent;
    static ArrayList<Integer> truth = new ArrayList<>();
    static ArrayList<Pair> graph = new ArrayList<>();
    static ArrayList<Integer>[] party;

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        party = new ArrayList[m];

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());

        for(int i = 0; i < cnt; i++) {
            truth.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            party[i] = new ArrayList<>();

            int src = 0;
            for(int j = 0; j < num; j++) {
                int v = Integer.parseInt(st.nextToken());

                if(j == 0) {
                    src = v;
                } else {
                    graph.add(new Pair(src, v));
                }

                party[i].add(v);
            }

        }

        for(int i = 0; i < graph.size(); i++) {
            Pair p = graph.get(i);

            if(getParent(p.x) == getParent(p.y)) continue;

            union(p.x, p.y);
        }

        for(int i = 0; i < m; i++) {
            int x = getParent(party[i].get(0));
            boolean flag = false;

            for(int j = 0; j < cnt; j++) {
                if(x == getParent(truth.get(j))) {
                    flag = true;
                    break;
                }
            }

            if(!flag) {
                ans++;
            }

        }

        System.out.println(ans);

    }

    public static int getParent(int x) {
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }

    public static void union(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

}
