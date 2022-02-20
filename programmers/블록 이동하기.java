import java.util.*;

class Robot implements Comparable<Robot> {
    int x1;
    int y1;
    int x2;
    int y2;
    int time;
    int dir;
    
    public Robot(int x1, int y1, int x2, int y2, int time, int dir) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.time = time;
        this.dir = dir;
    }
    
    public int compareTo(Robot r) {
        return time - r.time;
    }
    
    public boolean equals(Object obj) {
        Robot r = (Robot) obj;
        
        if (x1 == r.x1 && y1 == r.y1 && x2 == r.x2 && y2 == r.y2) {
            return true;
        }
        return false;
    }
    
}

class Solution {
    int n;
    int[][] graph;
    int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };
    int[] width_0 = { -1, 1, -1, 1 }, height_0 = { -1, -1, 1, 1 };
    int[] width_1 = { -1, -1, 1, 1 }, height_1 = { -1, 1, -1, 1 };
    
    public int solution(int[][] board) {
        n = board.length;
        graph = board;
        
        return solve();
    }
    
    public int solve() {
        Queue<Robot> q = new LinkedList<>();
        List<Robot> visited = new ArrayList<>();
        Robot robot = new Robot(0, 0, 0, 1, 0, 0);
        q.add(robot);
        visited.add(robot);
        
        while (!q.isEmpty()) {
            Robot r = q.poll();
            
            if ((r.x1 == n - 1 && r.y1 == n - 1) || (r.x2 == n - 1 && r.y2 == n - 1)) {
                return r.time;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx1 = r.x1 + dx[i];
                int ny1 = r.y1 + dy[i];
                int nx2 = r.x2 + dx[i];
                int ny2 = r.y2 + dy[i];
                
                if (!checkRange(nx1, ny1) || !checkRange(nx2, ny2)) continue;
                if (checkWall(nx1, ny1) || checkWall(nx2, ny2)) continue;
                
                robot = new Robot(nx1, ny1, nx2, ny2, r.time + 1, r.dir);
                if (visited.contains(robot)) continue;
                
                visited.add(robot);
                q.add(robot);
            }
            
            if (r.dir == 0) {
                for (int i = 0; i < 2; i++) {
                    int x = r.x2 + width_0[i];
                    int y = r.y2 + height_0[i];
                    
                    if (!checkRange(x, y) || checkWall(x, r.y2) || checkWall(x, y)) continue;
                    
                    if(i == 0) robot = new Robot(x, y, r.x1, r.y1, r.time + 1, 1);
                    else robot = new Robot(r.x1, r.y1, x, y, r.time + 1, 1);
                    
                    if (visited.contains(robot)) continue;
                    visited.add(robot);
                    q.add(robot);
                }
                
                for (int i = 2; i < 4; i++) {
                    int x = r.x1 + width_0[i];
                    int y = r.y1 + height_0[i];
                    
                    if (!checkRange(x, y) || checkWall(x, r.y1) || checkWall(x, y)) continue;
                    
                    if (i == 2) robot = new Robot(x, y, r.x2, r.y2, r.time + 1, 1);
                    else robot = new Robot(r.x2, r.y2, x, y, r.time + 1, 1);
                    
                    if (visited.contains(robot)) continue;
                    visited.add(robot);
                    q.add(robot);
                }
            } else {          
                for (int i = 0; i < 2; i++) {
                    int x = r.x2 + width_1[i];
                    int y = r.y2 + height_1[i];
                    
                    if (!checkRange(x, y) || checkWall(r.x2, y) || checkWall(x, y)) continue;
                    
                    if (i == 0) robot = new Robot(x, y, r.x1, r.y1, r.time + 1, 0);
                    else robot = new Robot(r.x1, r.y1, x, y, r.time + 1, 0);
                    
                    if (visited.contains(robot)) continue;
                    visited.add(robot);
                    q.add(robot);
                }
                
                for (int i = 2; i < 4; i++) {
                    int x = r.x1 + width_1[i];
                    int y = r.y1 + height_1[i];
                    
                    if (!checkRange(x, y) || checkWall(r.x1, y) || checkWall(x, y)) continue;
                    
                    if (i == 2) robot = new Robot(x, y, r.x2, r.y2, r.time + 1, 0);
                    else robot = new Robot(r.x2, r.y2, x, y, r.time + 1, 0);
                    
                    if (visited.contains(robot)) continue;
                    visited.add(robot);
                    q.add(robot);
                }
            }
            
        }
        
        return -1;
    }
    
    public boolean checkRange(int x, int y) {
         if (x < 0 || x >= n || y < 0 || y >= n) return false;
        return true;
    }
    
    public boolean checkWall(int x, int y) {
        if (graph[x][y] == 1) return true;
        return false;
    }
    
}