import java.io.*;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair p) {
            return a - p.a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            ArrayList<Pair> list = new ArrayList<>();

            for(int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list.add(new Pair(a, b));
            }

            Collections.sort(list);

            int cnt = 1;
            Pair comp = list.get(0);

            for(int j = 1; j < list.size(); j++) {
                Pair p = list.get(j);
                if(comp.b > p.b) {
                    cnt++;
                    comp = p;
                }
            }

            System.out.println(cnt);

        }


    }

}
