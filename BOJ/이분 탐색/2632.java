import java.io.*;
import java.util.*;

public class Main {
    static int size, n, m;
    static int[] pizzaA, pizzaB;
    static ArrayList<Integer> listA, listB;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        size = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pizzaA = new int[n];
        pizzaB = new int[m];
        listA = new ArrayList<>();
        listB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            pizzaA[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < m; i++) {
            pizzaB[i] = Integer.parseInt(br.readLine());
        }

        makeAllCases(pizzaA, pizzaA.length, listA);
        makeAllCases(pizzaB, pizzaB.length, listB);

        Collections.sort(listA);
        Collections.sort(listB);

        long answer = 0;
        for (int i = 0; i < listA.size(); i++) {
            int a = listA.get(i);
            if (size - a <= 0) continue;
            answer += upperBound(listB, size - a) - lowerBound(listB, size - a);
        }

        for (int v : listA) {
            if (v == size) answer++;
        }
        for (int v : listB) {
            if (v == size) answer++;
        }

        System.out.println(answer);
    }

    static long lowerBound(ArrayList<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    static long upperBound(ArrayList<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    static void makeAllCases(int[] pizza, int len, ArrayList<Integer> list) {
        for (int i = 0; i < len; i++) {
            int sum = pizza[i];
            list.add(sum);

            int k = i == 0 ? len - 1 : len - 2;
            for (int j = 1; j <= k; j++) {
                int idx = (i + j) % len;
                sum += pizza[idx];
                list.add(sum);
            }
        }
    }

}
