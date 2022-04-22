import java.io.*;
import java.util.*;

public class Main {
    static int n, h;
    static Pair[] arr;

    static class Pair {
        int type;
        int weight;
        int hp;

        public Pair(int type, int weight, int hp) {
            this.type = type;
            this.weight = weight;
            this.hp = hp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        arr = new Pair[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        long left = 0, right = Long.MAX_VALUE;
        while (left < right) {
            long mid = (left + right) / 2;
            long currentHp = mid;
            long strength = h;

            for (int i = 0; i < n; i++) {
                Pair p = arr[i];

                if (p.type == 1) {
                    long offense = (long) Math.ceil((double) p.hp / strength);
                    long defense = (long) Math.ceil((double) currentHp / p.weight);

                    if (offense <= defense) {
                        currentHp -= (p.weight * (offense - 1));
                    } else {
                        currentHp = -1;
                        break;
                    }
                } else {
                    strength += p.weight;
                    currentHp = Math.min(mid, currentHp + p.hp);
                }
            }

            if (currentHp > 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(right);
    }

}
