import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] board;
    static Pair[] currentLocation;
    static int[] currentDir;
    static Smell[][] smellBoard;
    static int[][][] dirPriority;
    static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Smell {
        int num;
        int sec;

        public Smell(int num, int sec) {
            this.num = num;
            this.sec = sec;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        currentLocation = new Pair[m + 1];
        currentDir = new int[m + 1];
        smellBoard = new Smell[n][n];
        dirPriority = new int[m + 1][4][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) {
                    currentLocation[board[i][j]] = new Pair(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            currentDir[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    dirPriority[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        System.out.println(solve());
    }

    static int solve() {
        spreadSmell();

        for (int i = 1; i <= 1000; i++) {
            moveShark();
            decreaseSmellSec();
            spreadSmell();
            if (checkShark()) return i;
        }

        return -1;
    }

    static boolean checkShark() {
        for (int i = 2; i <= m; i++) {
            if (currentLocation[i] != null) return false;
        }
        return true;
    }

    static void spreadSmell() {
        for (int i = 1; i <= m; i++) {
            Pair p = currentLocation[i];
            if (p == null) continue;

            smellBoard[p.x][p.y] = new Smell(i, k);
        }
    }

    static void moveShark() {
        for (int i = m; i >= 1; i--) {
            Pair p = currentLocation[i];

            if (p == null) continue;

            int dir = currentDir[i];
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                int nx = p.x + dx[j];
                int ny = p.y + dy[j];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (smellBoard[nx][ny] == null) {
                    list.add(j);
                }
            }

            if (list.isEmpty()) {
                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if (smellBoard[nx][ny] != null && smellBoard[nx][ny].num == i) {
                        list.add(j);
                    }
                }
            }

            int next = -1;
            int[] priority = dirPriority[i][dir];
            for (int j = 0; j < 4; j++) {
                if (list.contains(priority[j])) {
                    next = priority[j];
                    break;
                }
            }

            Pair nextLocation = new Pair(p.x + dx[next], p.y + dy[next]);

            for (int j = 1; j <= m; j++) {
                if (i == j || currentLocation[j] == null) continue;
                if (currentLocation[j].x == nextLocation.x && currentLocation[j].y == nextLocation.y) {
                    currentLocation[j] = null;
                }
            }

            currentLocation[i] = nextLocation;
            currentDir[i] = next;
        }

    }

    static void decreaseSmellSec() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (smellBoard[i][j] == null) continue;
                smellBoard[i][j].sec -= 1;

                if (smellBoard[i][j].sec == 0) smellBoard[i][j] = null;
            }
        }
    }

}
