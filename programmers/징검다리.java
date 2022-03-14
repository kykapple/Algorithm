import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int[] arr = new int[rocks.length + 2];
        int[] prev = new int[rocks.length + 2];
        
        arr[0] = 0;
        arr[rocks.length + 1] = distance;
        for (int i = 0; i < rocks.length; i++) {
            arr[i + 1] = rocks[i];
        }
        
        int left = 0, right = distance, answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            
            for (int i = 1; i < prev.length; i++) {
                prev[i] = i - 1;
            }
            
            for (int i = 1; i < arr.length; i++) {
                int prevIdx = prev[i];
                if (arr[i] - arr[prevIdx] < mid) {
                    if (i < arr.length - 1) prev[i + 1] = prev[i];
                    cnt++;
                }
            }
            
            if (cnt <= n) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
}