import java.io.*;
import java.util.*;

public class Main {
    static int n, answer;
    static PriorityQueue<Period> pq;

    static class Period {
        int startX, startY, endX, endY;

        public Period(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.startX == p2.startX) {
                if (p1.startY == p2.startY) {
                    if (p1.endX == p2.endX) return p2.endY - p1.endY;
                    return p2.endX - p1.endX;
                }
                return p1.startY - p2.startY;
            }
            return p1.startX - p2.startX;
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            pq.add(new Period(startX, startY, endX, endY));
        }

        int currMonth = 3, currDate = 1;
        boolean flag = true;

        while (!pq.isEmpty()) {
            int size = pq.size();
            Period p = null;

            while (!pq.isEmpty() && containsCurrDate(pq.peek(), currMonth, currDate)) {
                Period temp = pq.poll();

                if (p == null) p = temp;
                else {
                    if (p.endX < temp.endX || (p.endX == temp.endX && p.endY < temp.endY)) {
                        p = temp;
                    }
                }
            }

            if (size == pq.size()) {
                flag = false;
                break;
            }

            currMonth = p.endX;
            currDate = p.endY;
            answer += 1;

            if (currMonth == 12) {
                break;
            }
        }

        if (!flag || currMonth < 12) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }

    static boolean containsCurrDate(Period p, int month, int date) {
        if (p.startX > month || (p.startX == month && p.startY > date)) {
            return false;
        }
        return true;
    }
}
