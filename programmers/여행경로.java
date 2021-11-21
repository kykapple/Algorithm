import java.util.*;

class Solution {
    List<String> ans = new ArrayList<>();
    List<Pair> list = new ArrayList<>();
    boolean visited[];
    boolean flag;
    
    class Pair implements Comparable<Pair> {
        String from;
        String to;
        
        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }
        
        @Override
        public int compareTo(Pair p) {
            if(from.compareTo(p.from) == 0) {
                return to.compareTo(p.to);
            }
            return from.compareTo(p.from);
        }
    }
    
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        visited = new boolean[tickets.length];
        
        for(int i = 0; i < tickets.length; i++) {
            list.add(new Pair(tickets[i][0], tickets[i][1]));
        }
        
        Collections.sort(list);
        
        dfs("ICN", new ArrayList<>());
        
        for(int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
    
    public void dfs(String cur, ArrayList<String> path) {
        path.add(cur);
        if(path.size() == list.size() + 1) {
            ans = path;
            flag = true;
            return;
        }
        
        for(int i = 0; i < list.size(); i++) {
            Pair p = list.get(i);
            if(!visited[i] && cur.equals(p.from)) {
                visited[i] = true;
                dfs(p.to, path);
                
                if(flag) return;
                
                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
        
    }
}
