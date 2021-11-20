class Solution {
    int answer = Integer.MAX_VALUE;
    boolean visited[];
    
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
    
        dfs(begin, target, words, 0);
        
        if(answer == Integer.MAX_VALUE)
            return 0;
        
        return answer;
    }
    
    public void dfs(String cur, String target, String[] words, int cnt) {
        if(cur.equals(target)) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        for(int i = 0; i < words.length; i++) {
            if(!visited[i] && check(cur, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }   
        }
    }
    
    public boolean check(String str1, String str2) {
        int diff = 0;
        
        for(int i = 0; i < str1.length(); i++) {
            if(str1.charAt(i) != str2.charAt(i)) {
                diff++;
            }
            
            if(diff > 1) {
                return false;
            }
        }
        
        return true;
    }
    
}
