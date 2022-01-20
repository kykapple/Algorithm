import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int g = Integer.parseInt(st.nextToken());
        ArrayList<Integer> list = new ArrayList<>();

        int left = 1, right = 1;
        while (true) {
            int result = right * right - left * left;
            if (result > 100000 && right - left == 1) break;

            if (result == g) {
                list.add(right);
                left++;
            } else if (result < g) {
                right++;
            } else {
                left++;
            }
        }

        if (list.size() != 0) {
            for (int v : list) {
                System.out.println(v);
            }
        } else {
            System.out.println(-1);
        }

    }

}
