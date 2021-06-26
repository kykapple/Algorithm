import java.util.*;

class Solution {
    int key_start;
    List<String> list = new ArrayList<>();
    Map<String, Boolean> candidate_key = new HashMap<>();
    
    public int solution(String[][] relation) {
        int answer = 0;
        int range = relation[0].length;     // 컬럼 개수
        
        tracking(0, range, "");
        
        Collections.sort(list, new Comparator<>() {
            public int compare(String s1, String s2) {
                if(s1.length() == s2.length())
                    return s1.compareTo(s2);
                else
                    return s1.length() - s2.length();
            }
        });
        
        for(int k=0; k<list.size(); k++) {
            Map<String, Boolean> map = new HashMap<>();
            String set = list.get(k);       // key조합
            
            boolean flag = true;
            for(int i=0; i<relation.length; i++) {
                String key = "";
                
                for(int j=0; j<set.length(); j++) {
                    int idx = set.charAt(j) - '0';
                    key += relation[i][idx];
                }
                
                if(map.get(key) == null) {
                    map.put(key, true);
                } else {
                    flag = false;
                    break;
                }
            }
            
            // key조합이 후보키인 경우
            if(flag) {
                // 최소성 만족 여부 확인
                if(check(0, set.length(), set, "")) {
                    answer++;
                    candidate_key.put(set, true);
                }
            }
        }
        
        return answer;
    }
    
    public boolean check(int idx, int range, String key, String str) {
        if(str.length() != 0) {
            if(candidate_key.get(str) != null)
                return false;
        }
        
        for(int i=idx; i<range; i++) {
            if(!check(i+1, range, key, str + key.charAt(i)))
                return false;
        }
        
        return true;
    }
    
    // 컬럼의 모든 조합 만들기
    public void tracking(int idx, int range, String str) {        
        if(str.length() != 0)
            list.add(str);
        
        for(int i=idx; i<range; i++) {                
            tracking(i+1, range, str + i);
        }
    }
}