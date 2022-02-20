import java.util.*;

class Solution {
    Map<String, ArrayList<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for (int i = 0; i < info.length; i++) {
            String[] arr = info[i].split(" ");
            makeAllCases(arr, "", 0, Integer.parseInt(arr[4]));
        }
        
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        for (int i = 0; i < query.length; i++) {
            String[] str = query[i].replace(" and ", "").split(" ");
            ArrayList<Integer> list = map.get(str[0]);
            if (list == null) continue;
            
            int score = Integer.parseInt(str[1]);
            
            int left = 0, right = list.size();
            while (left < right) {
                int mid = (left + right) / 2;
                
                if (list.get(mid) >= score) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            
            answer[i] = list.size() - right;
        }
        
        return answer;
    }
    
    public void makeAllCases(String[] arr, String curr, int idx, int score) {
        if (idx == 4) {
            if (!map.containsKey(curr)) {
                map.put(curr, new ArrayList<>());
            }
            map.get(curr).add(score);
            return;
        }
        
        makeAllCases(arr, curr + arr[idx], idx + 1, score);
        makeAllCases(arr, curr + "-", idx + 1, score);
    }
}
