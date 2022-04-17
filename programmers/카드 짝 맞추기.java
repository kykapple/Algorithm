import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int[][] board;
    boolean[] visited;
    ArrayList<Integer> list;
    Map<Integer, ArrayList<Pair>> map;
    int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };
    
    class Pair {
        int x;
        int y;
        int cnt;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public Pair(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[][] board, int r, int c) {
        this.board = board;
        map = new HashMap<>();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    if (map.get(board[i][j]) == null) {
                        map.put(board[i][j], new ArrayList<>());
                    }
                    map.get(board[i][j]).add(new Pair(i, j));
                }
            }
        }
        
        visited = new boolean[map.size()];
        list = new ArrayList<>(map.keySet());
        
        solve(new Pair(r, c), 0, 0);
        
        return answer;
    }
    
    public void solve(Pair curr, int cnt, int sum) {
        if (cnt == list.size()) {
            answer = Math.min(answer, sum);
            return;
        }
        
        for (int i = 0; i < list.size(); i++) {
            if (visited[i]) continue;
            
            visited[i] = true;
            
            int cardNum = list.get(i);
            ArrayList<Pair> cards = map.get(cardNum);
            
            int dist = bfs(curr, cards.get(0)) + bfs(cards.get(0), cards.get(1)) + 2;
            solve(cards.get(1), cnt + 1, sum + dist);
            
            for (Pair p : cards) {
                board[p.x][p.y] = cardNum;
            }
            
            dist = bfs(curr, cards.get(1)) + bfs(cards.get(1), cards.get(0)) + 2;
            solve(cards.get(0), cnt + 1, sum + dist);
                  
            for (Pair p : cards) {
                board[p.x][p.y] = cardNum;
            }   
            
            visited[i] = false;
        }
        
    }
    
    public int bfs(Pair start, Pair end) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] check = new boolean[4][4];
        
        q.add(new Pair(start.x, start.y, 0));
        check[start.x][start.y] = true;
        
        while (!q.isEmpty()) {
            Pair p = q.poll();
            
            if (p.x == end.x && p.y == end.y) {
                board[p.x][p.y] = 0;
                return p.cnt;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if (outOfRange(nx, ny) || check[nx][ny]) continue;
                
                check[nx][ny] = true;
                q.add(new Pair(nx, ny, p.cnt + 1));
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = p.x;
                int ny = p.y;
                
                while (!outOfRange(nx + dx[i] , ny + dy[i])) {
                    nx += dx[i];
                    ny += dy[i];
                    if (board[nx][ny] != 0) break;
                }
                
                if (check[nx][ny]) continue;
                
                check[nx][ny] = true;
                q.add(new Pair(nx, ny, p.cnt + 1));
            }
            
        }
        
        return -1;
    }
    
    public boolean outOfRange(int nx, int ny) {
        return nx < 0 || nx >= 4 || ny < 0 || ny >= 4;
    }
    
}
