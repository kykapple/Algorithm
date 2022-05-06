import java.util.*;

class Solution {
    int answer = 0;
    int[] info;
    ArrayList<ArrayList<Integer>> graph;
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        graph = new ArrayList<>();
        
        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        
        solve(0, 0, 0, new ArrayList<>());
        
        return answer;
    }
    
    public void solve(int curr, int sheep, int wolf, ArrayList<Integer> list) {
        if (info[curr] == 0) sheep += 1;
        else wolf += 1;
        
        if (wolf >= sheep) return;
        answer = Math.max(answer, sheep);
        
        ArrayList<Integer> newList = new ArrayList<>();
        newList.addAll(list);
        newList.addAll(graph.get(curr));
        
        for (int i = 0; i < newList.size(); i++) {
            int next = newList.remove(i);
            solve(next, sheep, wolf, newList);
            newList.add(i, next);
        }
    }
    
}
