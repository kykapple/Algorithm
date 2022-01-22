import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Main {
    static int n, k, alphabet;
    static String[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new String[n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            str = str.substring(4, str.length() - 4);
            arr[i] = str;
        }

        if (k < 5) {
            System.out.println(0);
            return;
        }

        init();
        k -= 5;

        System.out.println(solve(0, 0, alphabet));
    }

    static int solve(int idx, int cnt, int currentAlphabet) {
        if (cnt == k) {
            return checkWords(currentAlphabet);
        }

        int ans = 0;
        for (int i = idx; i < 26; i++) {
            if ((currentAlphabet & 1 << i) != 0) continue;
            ans = Math.max(ans, solve(i + 1, cnt + 1, currentAlphabet | 1 << i));
        }

        return ans;
    }

    static int checkWords(int currentAlphabet) {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            boolean flag = true;

            for (int j = 0; j < arr[i].length(); j++) {
                if ((currentAlphabet & 1 << arr[i].charAt(j) - 'a') == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                cnt++;
            }

        }

        return cnt;
    }

    static void init() {
        Stream.of('a', 'c', 'i', 'n', 't')
                .map(character -> character - 'a')
                .forEach(value -> alphabet = alphabet | 1 << value);
    }

}
