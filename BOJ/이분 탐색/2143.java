import java.io.*;
import java.util.*;

public class Main {
    static int t, n, m;
    static long ans;
    static int[] a, b;
    static ArrayList<Integer> listA, listB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());

        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        b = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        listA = new ArrayList<>();
        listB = new ArrayList<>();

        getAllCases(listA, a, n);
        getAllCases(listB, b, m);

        Collections.sort(listA);
        Collections.sort(listB);

        for(int i = 0; i < listB.size(); i++) {
            int v = listB.get(i);
            int target = t - v;

            int left = lowerBound(0, listA.size(), target);
            int right = upperBound(0, listA.size(), target);

            ans += right - left;
        }

        System.out.println(ans);

    }

    static void getAllCases(List<Integer> list, int[] arr, int range) {
        for(int i = 0; i < range; i++) {
            int v = arr[i];
            list.add(v);

            for(int j = i + 1; j < range; j++) {
                v += arr[j];
                list.add(v);
            }
        }
    }

    static int lowerBound(int left, int right, int target) {
        while(left < right) {
            int mid = (left + right) / 2;
            int v = listA.get(mid);

            if(v >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    static int upperBound(int left, int right, int target) {
        while(left < right) {
            int mid = (left + right) / 2;
            int v = listA.get(mid);

            if(v <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
