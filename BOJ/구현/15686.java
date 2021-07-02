import java.io.*;
import java.util.*;

public class Main {
    static int n, m, ans = Integer.MAX_VALUE;
    static int map[][];
    static ArrayList<Pair> houses = new ArrayList<>();
    static ArrayList<Pair> chicken = new ArrayList<>();
    static ArrayList<Pair> select = new ArrayList<>();

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
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1)
                    houses.add(new Pair(i, j));
                else if(map[i][j] == 2)
                    chicken.add(new Pair(i, j));
            }
        }

        solve(0, 0);
        System.out.println(ans);
    }

    static void solve(int idx, int cnt) {
        if(cnt == m) {
            ans = Math.min(ans, calc());
            return;
        }

        for(int i=idx; i<chicken.size(); i++) {
            Pair p = chicken.get(i);
            select.add(p);
            solve(i+1, cnt+1);
            select.remove(p);
        }
    }

    static int calc() {
        int result = 0;

        for(int i=0; i<houses.size(); i++) {
            Pair house = houses.get(i);
            int temp = Integer.MAX_VALUE;

            for(int j=0; j<select.size(); j++) {
                Pair p = select.get(j);

                int distance = Math.abs(house.x - p.x) + Math.abs(house.y - p.y);
                temp = Math.min(temp, distance);
            }

            result += temp;
        }

        return result;
    }
}