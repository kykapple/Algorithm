import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < k; j++) {
                sb.append(st.nextToken()).append(' ');
            }

            list.add(sb.toString());
        }

        list.sort((s1, s2) -> s1.compareTo(s2));

        Map<String, Boolean> map = new HashMap<>();
        for (String str : list) {
            String[] arr = str.split(" ");
            StringBuilder curr = new StringBuilder(arr[0]);

            if (!map.containsKey(curr.toString())) {
                map.put(curr.toString(), true);
                System.out.println(curr);
            }

            for (int i = 1; i < arr.length; i++) {
                curr.append('-').append(arr[i]);
                if (i != arr.length - 1 && map.containsKey(curr.toString())) continue;

                map.put(curr.toString(), true);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 2 * i; j++) {
                    sb.append('-');
                }
                sb.append(arr[i]);

                System.out.println(sb);
            }
        }
    }

}
