import java.util.*;

class Solution {
    Map<String, Integer> idxMap = new HashMap<>();
    Map<String, Boolean> map = new HashMap<>();
    int[] arr;
    
    public int[] solution(String[] gems) {        
        int len = gems.length;
        for (int i = 0; i < len; i++) {
            if (idxMap.get(gems[i]) == null) {
                idxMap.put(gems[i], idxMap.size());
            }
        }
        
        arr = new int[idxMap.size()];
        
        int left = 0, right = 0, start = 0, end = Integer.MAX_VALUE;
        while (true) {
            if (map.size() == idxMap.size()) {
                if (right - left < end - start) {
                    start = left;
                    end = right;
                }
                int idx = idxMap.get(gems[left]);
                arr[idx]--;
                
                if (arr[idx] == 0) {
                    map.remove(gems[left]);
                }
                
                left++;
            } else {
                if (right >= len) break;
                int idx = idxMap.get(gems[right]);
                arr[idx]++;
                
                map.put(gems[right], true);
                
                right++;
            }
        }
        
        return new int[]{ start + 1, end };
    }
    
}