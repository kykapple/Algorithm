class Solution {
    int N, answer = Integer.MAX_VALUE;
    int[] Dist, Weak;
    boolean[] visited;
    
    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        Dist = dist;
        Weak = weak;
        visited = new boolean[dist.length];
        
        for (int i = 0; i < weak.length; i++) {
            Weak = rotate(Weak);
            solve(0, 0);
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    public void solve(int curr, int cnt) {
        if (curr == Weak.length) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        for (int i = 0; i < Dist.length; i++) {
            if (visited[i]) continue;
            
            visited[i] = true;
            
            int location = Weak[curr] + Dist[i];
            int next = curr;
            
            while (next + 1 < Weak.length && Weak[curr] < Weak[next + 1] && Weak[next + 1] <= location) {
                next += 1;
            }
            
            if (location >= N) {
                location %= N;
                while (next + 1 < Weak.length && Weak[next + 1] <= location) {
                    next += 1;
                }
            }
            
            solve(next + 1, cnt + 1);
            
            visited[i] = false;
        }
        
    }
    
    public int[] rotate(int[] arr) {
        int n = arr.length;
        int[] newArr = new int[n];
        
        for (int i = 0; i < n; i++) {
            newArr[(i + 1) % n] = arr[i];
        }
        
        return newArr;
    }
    
}
