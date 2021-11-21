import java.util.*;

class Solution {
    int dp[][];
    int dx[] = { 1, 0 }, dy[] = { 0, 1 };
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        dp = new int[n][m];
        
        dp[n - 1][m - 1] = 1;
        answer = dfs(0, 0, puddles, n, m);
        
        return answer;
    }

    public int dfs(int x, int y, int[][] puddles, int n, int m) {
        if(dp[x][y] != 0) return dp[x][y];
        
        for(int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx >= n || ny >= m) continue;
            if(isPuddle(nx, ny, puddles)) continue;
               
            dp[x][y] += dfs(nx, ny, puddles, n, m) % 1000000007;
        }
        
        return dp[x][y] % 1000000007;
    }
    
    public boolean isPuddle(int x, int y, int[][] puddles) {
        for(int i = 0; i < puddles.length; i++) {
            if(x == puddles[i][1] - 1 && y == puddles[i][0] - 1) {
                return true;
            }
        }
        return false;
    }
    
}
