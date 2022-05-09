import java.util.*;

class Solution {
    Map<String, Integer> strMap = new HashMap<>();
    Map<Integer, Integer> maxCntMap = new HashMap<>();
    ArrayList<String> answer = new ArrayList<>();
    
    public String[] solution(String[] orders, int[] course) {
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            solve(arr, 0, "");
        }
        
        for (int i = 0; i < course.length; i++) {
            int len = course[i];
            
            for (String str : strMap.keySet()) {
                if (str.length() != len) continue;
                
                if (maxCntMap.get(len) >= 2 && strMap.get(str) == maxCntMap.get(len)) {
                    answer.add(str);
                }
            }
        }
        
        Collections.sort(answer);
        
        return answer.toArray(String[]::new);
    }
    
    public void solve(char[] arr, int idx, String str) {
        if (str.length() >= 2) {
            int len = str.length();
            
            strMap.put(str, strMap.getOrDefault(str, 0) + 1);
            maxCntMap.put(len, Math.max(maxCntMap.getOrDefault(len, 0), strMap.get(str)));
        }
        
        for (int i = idx; i < arr.length; i++) {
            solve(arr, i + 1, str + arr[i]);
        }
        
    }
    
}
