import java.io.*;
import java.util.*;

public class Main {
    static int t, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            String[] arr = new String[n];
            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine();
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < arr[i].length(); j++) {
                    sb.append(arr[i].charAt(j));
                    map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
                }
            }

            boolean flag = true;
            for (int i = 0; i < arr.length; i++) {
                if (map.get(arr[i]) > 1) {
                    flag = false;
                    break;
                }
            }

            if(flag) System.out.println("YES");
            else System.out.println("NO");
        }
    }

}
