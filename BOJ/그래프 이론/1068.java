import java.io.*;
import java.util.*;

public class Main {
    static int n, root, deleteNodeNum, cnt;
    static Map<Integer, ArrayList<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue;
            }
            map.get(parent).add(i);
        }

        st = new StringTokenizer(br.readLine());
        deleteNodeNum = Integer.parseInt(st.nextToken());

        if (root == deleteNodeNum) {
            System.out.println(0);
            return;
        }

        solve(root);

        System.out.println(cnt);
    }

    static void solve(int curr) {
        int children = 0;
        for (int i = 0; i < map.get(curr).size(); i++) {
            int child = map.get(curr).get(i);

            if (child != deleteNodeNum) {
                children += 1;
                solve(child);
            }
        }
        if (children == 0) {
            cnt += 1;
        }
    }

}
