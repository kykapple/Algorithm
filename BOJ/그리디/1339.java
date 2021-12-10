import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Integer[] arr = new Integer[26];
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            list.add(br.readLine());
        }

        for(int i = 0; i < 26; i++) {
            arr[i] = 0;
        }

        for(int i = 0 ; i < list.size(); i++) {
            String str = list.get(i);
            int v = 1;

            for(int j = str.length() - 1; j >= 0; j--) {
                char ch = str.charAt(j);
                arr[ch - 65] += v;
                v *= 10;
            }
        }

        Arrays.sort(arr, (a, b) -> b - a);

        int num = 9, ans = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) continue;
            ans += arr[i] * num;
            num--;
        }

        System.out.println(ans);
    }

}
