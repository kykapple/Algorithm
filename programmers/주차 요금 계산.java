import java.util.*;
import java.time.LocalTime;

class Solution {
    Map<String, LocalTime> in = new HashMap<>();
    Map<String, Boolean> out = new HashMap<>();
    Map<String, Integer> sum = new HashMap<>();
    ArrayList<Pair> list = new ArrayList<>();
    
    class Pair implements Comparable<Pair> {
        String num;
        int fee;
        
        public Pair(String num, int fee) {
            this.num = num;
            this.fee = fee;
        }
        
        public int compareTo(Pair p) {
            return Integer.parseInt(num) - Integer.parseInt(p.num);
        }
    }
    
    public int[] solution(int[] fees, String[] records) {        
        for (int i = 0; i < records.length; i++) {
            String[] arr = records[i].split(" ");
            String[] times = arr[0].split(":");
            int hour = Integer.parseInt(times[0]);
            int min = Integer.parseInt(times[1]);
            
            if (arr[2].equals("IN")) {
                in.put(arr[1], LocalTime.of(hour, min));
                out.put(arr[1], false);
            } else {
                out.put(arr[1], true);
                LocalTime inTime = in.get(arr[1]);
                int gap = (LocalTime.of(hour, min).toSecondOfDay() - inTime.toSecondOfDay()) / 60;
                sum.put(arr[1], sum.getOrDefault(arr[1], 0) + gap);
            }
        }
        
        for (String key : in.keySet()) {
            if (!out.get(key)) {
                int gap = (LocalTime.of(23, 59).toSecondOfDay() - in.get(key).toSecondOfDay()) / 60;
                sum.put(key, sum.getOrDefault(key, 0) + gap);
            }
        }
        
        for (String key : sum.keySet()) {
            int parkTime = sum.get(key);
            
            if (parkTime <= fees[0]) {
                list.add(new Pair(key, fees[1]));
            } else {
                int over = (int) Math.ceil((parkTime - fees[0]) / (double) fees[2]);
                int fee = fees[1] + over * fees[3]; 
                list.add(new Pair(key, fee));
            }
        }
        
        Collections.sort(list);
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).fee;
        }
        
        return answer;
    }
}
