import java.util.*;

class Solution {
    int n;
    ArrayList<String> answer;
    Map<String, ArrayList<String>> map;
    Map<String, boolean[]> visited;
    
    public String[] solution(String[][] tickets) {
        n = tickets.length;
        answer = new ArrayList<>();
        map = new HashMap<>();
        visited = new HashMap<>();
        
        for (int i = 0; i < tickets.length; i++) {
            if (map.get(tickets[i][0]) == null) {
                map.put(tickets[i][0], new ArrayList<>());
            }
            map.get(tickets[i][0]).add(tickets[i][1]);
        }
        
        for (String str : map.keySet()) {
            ArrayList<String> list = map.get(str);
            visited.put(str, new boolean[list.size()]);
        }
        
        solve("ICN", "ICN", 0);
        Collections.sort(answer);
        
        return answer.get(0).split("-");
    }
    
    public void solve(String curr, String str, int cnt) {
        if (cnt == n) {
            answer.add(str);
            return;
        }
        
        ArrayList<String> list = map.get(curr);
        boolean[] check = visited.get(curr);
        if (list == null) {
            return;
        }
        
        for (int i = 0; i < list.size(); i++) {
            String next = list.get(i);
            if (!check[i]) {
                check[i] = true;
                solve(next, str + "-" + next, cnt + 1);
                check[i] = false;
            }
        }
    }
}
