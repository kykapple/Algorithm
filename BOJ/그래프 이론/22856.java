import java.io.*;
import java.util.*;

public class Main {
    static int n, des;
    static boolean[] visited;
    static Map<Integer, Node> map;
    static ArrayList<Integer> inOrderList;

    static class Node {
        int num;
        Node parent;
        Node left;
        Node right;

        public Node(int num) {
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        map = new HashMap<>();
        inOrderList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (!map.containsKey(a)) map.put(a, new Node(a));
            if (!map.containsKey(b)) map.put(b, new Node(b));
            if (!map.containsKey(c)) map.put(c, new Node(c));

            Node parent = map.get(a);

            if (b != -1) {
                parent.left = map.get(b);
                map.get(b).parent = parent;
            }
            if (c != -1) {
                parent.right = map.get(c);
                map.get(c).parent = parent;
            }
        }

        inOrder(map.get(1));
        des = inOrderList.get(inOrderList.size() - 1);

        solve(map.get(1), 0);
    }

    static void solve(Node curr, int cnt) {
        Node parent = curr.parent;
        Node left = curr.left;
        Node right = curr.right;

        if (left != null && !visited[left.num]) {
            visited[left.num] = true;
            solve(left, cnt + 1);
        } else if (right != null && !visited[right.num]) {
            visited[right.num] = true;
            solve(right, cnt + 1);
        } else {
            if (curr.num == des) {
                System.out.println(cnt);
                return;
            }
            if (parent != null) {
                visited[parent.num] = true;
                solve(parent, cnt + 1);
            }
        }

    }

    static void inOrder(Node curr) {
        if (curr.left != null) {
            inOrder(curr.left);
        }

        inOrderList.add(curr.num);

        if (curr.right != null) {
            inOrder(curr.right);
        }
    }

}
