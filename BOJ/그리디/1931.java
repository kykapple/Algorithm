import java.io.*;
import java.util.*;

public class Main {
    static int n, cnt;
    static ArrayList<Pair> list = new ArrayList<>();

    static class Pair implements Comparable<Pair> {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair p) {
            if(end == p.end) {
                return start - p.start;
            }
            return end - p.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Pair(start, end));
        }

        Collections.sort(list);

        int cur = 0;
        for(int i = 0; i < list.size(); i++) {
            Pair p = list.get(i);
            if(p.start < cur) continue;

            cur = p.end;
            cnt++;
        }

        System.out.println(cnt);
    }

}
