import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[][] board;
    static Pair[] location;
    static Deque<Integer>[][] dq;
    static int[] dx = { 0, 0, -1, 1 }, dy = { 1, -1, 0, 0 };

    static class Pair {
        int x;
        int y;
        int dir;

        public Pair(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        location= new Pair[k];
        dq = new Deque[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dq[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;

            location[i] = new Pair(x, y, dir);
            dq[x][y].add(i);
        }

        System.out.println(solve());
    }

    static int solve() {
        for (int t = 1; t <= 1000; t++) {
            for (int i = 0; i < k; i++) {
                Pair p = location[i];

                int nx = p.x + dx[p.dir];
                int ny = p.y + dy[p.dir];

                if (isBlue(nx, ny)) {
                    p.dir = turnBack(p.dir);
                    nx = p.x + dx[p.dir];
                    ny = p.y + dy[p.dir];

                    if (isBlue(nx, ny)) continue;
                }

                if (board[nx][ny] == 0) {
                    Deque<Integer> curr = dq[p.x][p.y];
                    boolean flag = false;
                    int size = curr.size();

                    for (int j = 0; j < size; j++) {
                        int unit = curr.pollFirst();
                        if (unit == i) flag = true;

                        if (flag) {
                            location[unit] = new Pair(nx, ny, location[unit].dir);
                            dq[nx][ny].addLast(unit);
                        }
                        else curr.addLast(unit);
                    }
                } else if (board[nx][ny] == 1) {
                    Deque<Integer> curr = dq[p.x][p.y];
                    int size = curr.size();

                    for (int j = 0; j < size; j++) {
                        int unit = curr.pollLast();
                        location[unit] = new Pair(nx, ny, location[unit].dir);
                        dq[nx][ny].addLast(unit);

                        if (unit == i) break;
                    }
                }

                if (dq[nx][ny].size() >= 4) return t;
            }
        }

        return -1;
    }

    static boolean isBlue(int nx, int ny) {
        if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 2) return true;
        return false;
    }

    static int turnBack(int dir) {
        switch(dir) {
            case 0: return 1;
            case 1: return 0;
            case 2: return 3;
            case 3: return 2;
        }
        return -1;
    }

}

