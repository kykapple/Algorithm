import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int parent[], count[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        parent = new int[1000001];
        count = new int[1000001];

        for(int i=0; i<=1000000; i++) {
            count[i] = 1;
            parent[i] = i;
        }

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());

            char menu = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());

            switch(menu) {
                case 'I':
                    int b = Integer.parseInt(st.nextToken());

                    if(find(a) != find(b)) {
                        union(a, b);
                    }
                    break;
                case 'Q':
                    bw.write(count[find(a)] + "\n");
                    break;
            }
        }

        br.close();
        bw.close();
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) {
            count[a] += count[parent[b]];
            parent[b] = a;
        }
        else {
            count[b] += count[parent[a]];
            parent[a] = b;
        }
    }

    static int find(int n) {
        if(parent[n] == n) return n;
        else return parent[n] = find(parent[n]);
    }
}