import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int[][] dp = new int[money.length+1][n+1];
        
        Arrays.sort(money);
        
        for(int i=1; i<=money.length; i++) {
            
            for(int j=1; j<=n; j++) {
                if(money[i-1] == j) dp[i][0] = 1;
                
                dp[i][j] += dp[i-1][j];
                
                if(money[i-1] <= j) {
                    dp[i][j] += dp[i][j-money[i-1]];
                }    
            }
        }
        
        return dp[money.length][n] % 1000000007;
    }
}