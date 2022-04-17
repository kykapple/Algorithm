import java.util.*;

class Solution {
    private static final int INF = 987654321;
    int[][] graph, dist;
    ArrayList<ArrayList<Integer>> list;
    
    class Node {
        int idx;
        int dist;
        int trap;
        
        public Node(int idx, int dist, int trap) {
            this.idx = idx;
            this.dist = dist;
            this.trap = trap;
        }
    }
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        dist = new int[n + 1][1 << 11];
        graph = new int[n + 1][n + 1];
        list = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            Arrays.fill(dist[i], INF);
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = INF;
                if (i == j) {
                    graph[i][j] = 0;
                }
            }
        }
        
        for (int i = 0; i < roads.length; i++) {
            int src = roads[i][0];
            int des = roads[i][1];
            int dist = roads[i][2];
            graph[src][des] = Math.min(graph[src][des], dist);
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (graph[i][j] != INF || graph[j][i] != INF) {
                    list.get(i).add(j);
                }
            }
        }
        
        return solve(start, end, traps);
    }
    
    public int solve(int start, int end, int[] traps) {
        PriorityQueue<Node> q = new PriorityQueue<>((n1, n2) -> n1.dist - n2.dist);
        q.add(new Node(start, 0, 0));
        dist[start][0] = 0;
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            int idx = node.idx;
            int trap = node.trap;
            
            if (idx == end) {
                return node.dist;
            }
            
            if (dist[idx][trap] < node.dist) continue;
            
            Map<Integer, Boolean> trapped = new HashMap<>();
            for (int i = 0; i < traps.length; i++) {
                if (traps[i] == idx) {
                    trap ^= (1 << i);
                }
                if ((trap & (1 << i)) != 0) {
                    trapped.put(traps[i], true);
                }
            }
            
            for (int i = 0; i < list.get(idx).size(); i++) {
                int next = list.get(idx).get(i);
                boolean currTrapped = trapped.containsKey(idx);
                boolean nextTrapped = trapped.containsKey(next);
                
                if (currTrapped == nextTrapped && graph[idx][next] != INF && dist[next][trap] > node.dist + graph[idx][next]) {
                    dist[next][trap] = node.dist + graph[idx][next];
                    q.add(new Node(next, dist[next][trap], trap));
                } else if (currTrapped != nextTrapped && graph[next][idx] != INF && dist[next][trap] > node.dist + graph[next][idx]) {
                    dist[next][trap] = node.dist + graph[next][idx];
                    q.add(new Node(next, dist[next][trap], trap));
                }
            }
            
        }
        
        return -1;
    }
    
}
