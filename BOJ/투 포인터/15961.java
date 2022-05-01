import java.io.*;
import java.util.*;

public class Main {
    static int n, d, k, c;
    static int[] arr;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];
        map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < k; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        map.put(c, map.getOrDefault(c, 0) + 1);

        int answer = map.size(), left = 1, right = k;
        while (left != 0) {
            if (map.get(arr[left - 1]) == 1) {
                map.remove(arr[left - 1]);
            } else {
                map.put(arr[left - 1], map.get(arr[left - 1]) - 1);
            }
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            answer = Math.max(answer, map.size());

            left = (left + 1) % n;
            right = (right + 1) % n;
        }

        System.out.println(answer);
    }
    
}
