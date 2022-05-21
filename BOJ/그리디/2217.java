import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Long> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(Long.parseLong(st.nextToken()));
        }

        list.sort(Collections.reverseOrder());

        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, list.get(i) * (i + 1));
        }

        System.out.println(answer);
    }

}
