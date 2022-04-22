import java.util.*;

class Solution {
    int[] info;
    int maxGap = -1, maxScore = 10;
    ArrayList<Integer> answer;
    
    public int[] solution(int n, int[] info) {
        this.info = info;
        
        solve(n, 0, "");
        
        if (answer == null || maxGap == 0) {
            return new int[] {-1};
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    public void solve(int n, int idx, String str) {
        if (idx == info.length) {
            String[] arr = str.split("");
            ArrayList<Integer> temp = new ArrayList<>();

            for (int i = 0; i <= maxScore; i++) {
                if (i == maxScore && n > 0) {
                    temp.add(Integer.parseInt(arr[i]) + n);
                } else {
                    temp.add(Integer.parseInt(arr[i]));
                }
            }
            
            int apeach = 0, lion = 0, currGap = 0;
            for (int i = 0; i <= maxScore; i++) {
                if (temp.get(i) == 0 && info[i] == 0) continue;
                
                if (temp.get(i) <= info[i]) apeach += maxScore - i;
                else lion += maxScore - i;
            }
            currGap = lion - apeach;
            
            if (apeach >= lion || maxGap > currGap) {
                return;
            }

            if (answer == null || maxGap < currGap) {
                maxGap = currGap;
                answer = temp;
                return;
            } 

            boolean flag = false;
            for (int i = maxScore; i >= 0; i--) {
                if (temp.get(i) == answer.get(i)) continue;
                if (temp.get(i) > answer.get(i)) {
                    flag = true;
                    break;
                } else {
                    break;
                }
            }

            if (flag) {
                answer = temp;
            }
            
            return;
        }
        
        if (info[idx] + 1 <= n) {
            int cnt = info[idx] + 1;
            solve(n - cnt, idx + 1, str + cnt); 
        }
        solve(n, idx + 1, str + 0);
        
    }
    
}
