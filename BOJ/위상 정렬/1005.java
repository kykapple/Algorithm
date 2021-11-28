import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
          
            int[] buildTime = new int[n + 1];
            int[] totalTime = new int[n + 1];
            int[] entry = new int[n + 1];
            ArrayList<Integer> graph[] = new ArrayList[n + 1];
            Queue<Integer> q = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                buildTime[j] = Integer.parseInt(st.nextToken());
                graph[j] = new ArrayList<>();
            }

            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from].add(to);
                entry[to]++;
            }

            st = new StringTokenizer(br.readLine());
            int des = Integer.parseInt(st.nextToken());

            for(int j = 1; j <= n; j++) {
                if(entry[j] == 0) {
                    q.add(j);
                    totalTime[j] = buildTime[j];
                }
            }

            while(!q.isEmpty()) {
                int x = q.poll();

                if(x == des) break;

                for(int j = 0; j < graph[x].size(); j++) {
                    int y = graph[x].get(j);
                    totalTime[y] = Math.max(totalTime[y], totalTime[x] + buildTime[y]);
                    if(--entry[y] == 0) {
                        q.add(y);
                    }
                }
            }

            System.out.println(totalTime[des]);

        }

    }

}
