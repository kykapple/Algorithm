import java.util.*;

class Solution {
    int n;
    int[][] graph;
    int dx[] = {-1,1,0,0}, dy[] = {0,0,-1,1};
    
    class Pair {
        int x;
        int y;
        int cost;
        int dir;
        
        public Pair(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }
    
    public int solution(int[][] board) {
        graph = board;
        n = board.length;
        
        return solve();
    }
    
    public int solve() {
        Queue<Pair> q = new LinkedList<>();
        int[][] cost = new int[n][n];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        
        q.add(new Pair(0, 0, 0, -1));
        cost[0][0] = 0;
        
        while(!q.isEmpty()) {
            Pair p = q.poll();
            
            if(p.x == n-1 && p.y == n-1) {
                cost[n-1][n-1] = Math.min(cost[n-1][n-1], p.cost);
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(graph[nx][ny] == 1) continue;
                
                // 직진하는 경우
                if(p.dir == -1 || p.dir == i) {
                    if(cost[nx][ny] >= p.cost + 100) {
                        cost[nx][ny] = p.cost + 100;
                        q.add(new Pair(nx, ny, cost[nx][ny], i));
                    }
                // 방향을 튼 경우
                } else {
                    if(cost[nx][ny] >= p.cost + 600) {
                        cost[nx][ny] = p.cost + 600;
                        q.add(new Pair(nx, ny, cost[nx][ny], i));
                    }
                }
            }
        }
        
        return cost[n-1][n-1];
    }
}