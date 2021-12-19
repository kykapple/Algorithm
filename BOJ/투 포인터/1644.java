import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        boolean check[] = new boolean[n + 1];
        ArrayList<Integer> primeList = new ArrayList<>();

        for(int i = 2; i <= n; i++) {
            if(check[i]) continue;

            for(int j = i + i; j <= n; j += i) {
                check[j] = true;
            }
        }

        for(int i = 2; i <= n; i++) {
            if(!check[i]) {
                primeList.add(i);
            }
        }

        int right = 0, sum = 0, ans = 0;
        for(int left = 0; left < primeList.size(); left++) {
            while(sum < n && right < primeList.size()) {
                sum += primeList.get(right++);
            }

            if(sum == n) {
                ans++;
            }
            sum -= primeList.get(left);
        }

        System.out.println(ans);

    }

}

