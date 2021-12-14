import java.io.*;
import java.util.*;

public class Main {
    static int k;
    static boolean result;
    static ArrayList<Integer> graph[];
    static boolean[] visited, flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph = new ArrayList[v + 1];
            visited = new boolean[v + 1];
            flag = new boolean[v + 1];
            result = false;

            for(int j = 0; j <= v; j++) {
                graph[j] = new ArrayList<>();
            }

            for(int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                graph[b].add(a);
            }

            for(int j = 1; j <= v; j++) {
                isBipartite(j);
            }

            if(result) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }

        }
    }

    static void isBipartite(int x) {
        visited[x] = true;

        for(int i = 0; i < graph[x].size(); i++) {
            int y = graph[x].get(i);
            if(!visited[y]) {
                flag[y] = !flag[x];
                isBipartite(y);
            }

            if(flag[x] == flag[y]) {
                result = true;
            }
        }
    }

}
