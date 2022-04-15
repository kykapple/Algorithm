import java.util.*;

class Solution {
    
    class Pair implements Comparable<Pair> {
        int idx;
        long foodTime;
        
        public Pair(int idx, long foodTime) {
            this.idx = idx;
            this.foodTime = foodTime;
        }
        
        public int compareTo(Pair p) {
            return (int) (foodTime - p.foodTime);
        }
    }
    
    public int solution(int[] food_times, long k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        for (int i = 0; i < food_times.length; i++) {
            pq.add(new Pair(i + 1, food_times[i]));
        }
        
        long minTime = pq.peek().foodTime, currTime = 0, size = pq.size();
        while (!pq.isEmpty() && (minTime - currTime) * size <= k) {            
            k -= (minTime - currTime) * size;
            while (!pq.isEmpty() && pq.peek().foodTime == minTime) {
                pq.poll();
            }
            currTime = minTime;
            minTime = !pq.isEmpty() ? pq.peek().foodTime : 0;
            size = pq.size();
        }
        
        if (!pq.isEmpty()) {
            ArrayList<Pair> list = new ArrayList<>();
            for (Pair p : pq) {
                list.add(p);
            }
            Collections.sort(list, (p1, p2) -> p1.idx - p2.idx);
            
            if (k > 0) {
                int idx = (long) list.size() <= k ? (int) (k % list.size()) : (int) k;
                return list.get(idx).idx;
            }
            return list.get(0).idx;
        }
        
        return -1;
    }
}
