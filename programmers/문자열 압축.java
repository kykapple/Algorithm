class Solution {
    
    public int getPlusValue(int cnt) {
        if(cnt == 1) return 0;
        else if(cnt < 10) return 1;
        else if(cnt < 100) return 2;
        else if(cnt < 1000) return 3;
        else return 4;
    }
    
    public int solution(String s) {
        int answer = s.length();
        
        for(int i=1; i<=s.length()/2; i++) {
            int len = 0, cnt = 1;
            String str = s.substring(0, i);
            
            for(int j=i; j<s.length(); j+=i) {
                if(j + i > s.length()) {
                    len += i;
                    len += getPlusValue(cnt);
                    len += s.length() - j;
                    cnt = 0;
                    break;
                }
                String next = s.substring(j, j+i);
                
                if(str.equals(next)) {
                    cnt++;
                } else {
                    str = next;
                    
                    len += i;
                    len += getPlusValue(cnt);
                    
                    cnt = 1;
                }
            }
            
            if(cnt != 0) {
                len += i;
                len += getPlusValue(cnt);
            }
            
            answer = Math.min(answer, len);
        }
        
        return answer;
    }
}