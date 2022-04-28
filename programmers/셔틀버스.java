import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int answer = 0;
        int busArrivedTime = convertToMin("09:00");
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < timetable.length; i++) {
            pq.add(convertToMin(timetable[i]));
        }

        for (int i = 0; i < n; i++) {
            int cnt = 0, time = 0;
            while (cnt < m && !pq.isEmpty()) {
                if (pq.peek() > busArrivedTime) break;
                time = pq.poll();
                cnt++;
            }

            if (i == n - 1) {
                if (cnt == m) answer = time - 1;
                else answer = busArrivedTime;
                break;
            }

            busArrivedTime += t;
        }

        return convertToString(answer);
    }

    public String convertToString(int time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }

    public int convertToMin(String time) {
        String[] arr = time.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
}
