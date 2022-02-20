class Solution {
    public int solution(String s) {
        int answer = 1001;
        int length = s.length();
        
        for (int i = 1; i <= length; i++) {
            String result = "";
            String prev = s.substring(0, i);
            int cnt = 1, j = i;
        
            for (j = i; j < length; j += i) {
                if (j + i > length) {
                    break;
                }
                
                String str = s.substring(j, j + i);
                
                if (str.equals(prev)) {
                    cnt++;
                } else {
                    result = cnt > 1 ? result + cnt + prev : result + prev;
                    prev = str;
                    cnt = 1;
                }
            }
            
            result = cnt > 1 ? result + cnt + prev : result + prev;
            result += s.substring(j, length);
            
            answer = Math.min(answer, result.length());
        }
        
        return answer;
    }
}
