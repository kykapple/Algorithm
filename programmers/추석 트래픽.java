class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(" ");
            double sec = convertToSec(line[1]) + 1;
            int cnt = 1;
            
            for (int j = i + 1; j < lines.length; j++) {
                String[] temp = lines[j].split(" ");
                double otherSec = convertToSec(temp[1]);
                double processingTime = Double.parseDouble(temp[2].substring(0, temp[2].length() - 1));
                double start = otherSec - processingTime + 0.001;
                
                if (sec > start) cnt++;
            }
            answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
    
    public double convertToSec(String time) {
        String[] str = time.split(":");
        return Double.parseDouble(str[0]) * 3600 + Double.parseDouble(str[1]) * 60 + Double.parseDouble(str[2]);
    }
    
}
