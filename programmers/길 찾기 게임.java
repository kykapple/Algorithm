import java.util.*;

class Solution {
    Node root;
    ArrayList<Integer> preList = new ArrayList<>();
    ArrayList<Integer> postList = new ArrayList<>();
    ArrayList<Node> depthList = new ArrayList<>();

    class Node {
        int idx;
        int num;
        int depth;
        Node left;
        Node right;
        
        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
        
        public Node(int idx, int num, int depth) {
            this.idx = idx;
            this.num = num;
            this.depth = depth;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        
        int rootIdx = 0, rootX = 0, maxY = -1;
        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            
            if (maxY < y) {
                maxY = y;
                rootIdx = i + 1;
                rootX = x;
            }
        }
        root = new Node(rootIdx, rootX);
        
        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            if (i + 1 == root.idx) continue;
            
            depthList.add(new Node(i + 1, x, y));
        }
        
        Collections.sort(depthList, (n1, n2) -> n2.depth - n1.depth);
        
        for (int i = 0; i < depthList.size(); i++) {
            insert(depthList.get(i));
        }
        
        preOrder(root);
        postOrder(root);
        
        int[][] answer = new int[2][preList.size()];
        
        for (int i = 0; i < preList.size(); i++) {
            answer[0][i] = preList.get(i);
        }
        
        for (int i = 0; i < postList.size(); i++) {
            answer[1][i] = postList.get(i);
        }
        
        return answer;
    }
    
    public void preOrder(Node curr) {
        preList.add(curr.idx);
        
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
        
        postList.add(curr.idx);
    }
    
    public void insert(Node el) {
        Node head = root;
        Node curr = root;
        
        while (true) {
            head = curr;
            if (curr.num > el.num) {
                curr = head.left;
                
                if (curr == null) {
                    head.left = el;
                    break;
                }
            } else {
                curr = head.right;
                
                if (curr == null) {
                    head.right = el;
                    break;
                } 
            }
        }
        
    }
    
}
