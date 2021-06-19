import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static ArrayList<Integer> graph[], reverse[];
    static int entry[], construct[];
    static ArrayList<Pair> list;

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        entry = new int[n+1];
        construct = new int[n+1];
        list = new ArrayList<>();

        graph = new ArrayList[n+1];
        reverse = new ArrayList[n+1];
        for(int i=0; i<=n; i++) {
            graph[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }


        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            reverse[b].add(a);
            entry[b]++;
        }

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int choice = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            list.add(new Pair(choice, num));
        }

        if(!solve())
            bw.write("Lier!\n");
        else
            bw.write("King-God-Emperor\n");

        br.close();
        bw.close();
    }

    static boolean solve() {
        for(int j=0; j<k; j++) {
            Pair p = list.get(j);
            int choice = p.x;
            int num = p.y;

            if(choice == 1) {
                if(entry[num] > 0) return false;

                construct[num]++;
                for(int i=0; i<graph[num].size(); i++) {
                    int y = graph[num].get(i);
                    entry[y]--;
                }

            } else if(choice == 2){
                if(construct[num] <= 0) return false;

                construct[num]--;
                for(int i=0; i<graph[num].size(); i++) {
                    int y =graph[num].get(i);
                    entry[y]++;
                }
            }
        }

        return true;
    }
}