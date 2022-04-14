import java.util.*;

class Solution {
    int preOrderIdx, postOrderIdx;
    Node root = null;
    List<Node> list = new ArrayList<>();
    int[][] answer;

    class Node implements Comparable<Node> {
        int x;
        int y;
        int idx;
        Node left;
        Node right;

        public Node(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }

        public int compareTo(Node p) {
            if (y == p.y) return x - p.x;
            return p.y - y;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];

        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            list.add(new Node(x, y, i + 1));
        }

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            insert(list.get(i));
        }

        preOrder(root);
        postOrder(root);

        return answer;
    }

    public void preOrder(Node curr) {
        answer[0][preOrderIdx++] = curr.idx;

        if (curr.left != null) {
            preOrder(curr.left);
        }

        if (curr.right != null) {
            preOrder(curr.right);
        }
    }

    public void postOrder(Node curr) {
        if (curr.left != null) {
            postOrder(curr.left);
        }

        if (curr.right != null) {
            postOrder(curr.right);
        }

        answer[1][postOrderIdx++] = curr.idx;
    }

    public void insert(Node newNode) {
        if (root == null) {
            root = newNode;
            return;
        }

        Node curr = root;
        Node parent = root;

        while (true) {
            parent = curr;
            if (curr.x < newNode.x) {
                curr = curr.right;
                if (curr == null) {
                    parent.right = newNode;
                    return;
                }
            } else {
                curr = curr.left;
                if (curr == null) {
                    parent.left = newNode;
                    return;
                }
            }

        }
    }

}
