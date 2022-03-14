import java.io.*;
import java.util.*;

public class Main {
    static int r, c, n;
    static char[][] board;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<ArrayList<Pair>> list;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < n; k++) {
            int row = Integer.parseInt(st.nextToken()) - 1;
            int clusterNum = 1;

            list = new ArrayList<>();
            list.add(new ArrayList<>());
            visited = new boolean[r][c];
            map = new int[r][c];

            row = r - 1 - row;
            throwStick(row, k % 2 == 0);

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (board[i][j] != '.' && !visited[i][j]) {
                        list.add(new ArrayList<>());
                        markCluster(i, j, clusterNum);
                        clusterNum++;
                    }
                }
            }

            for (int i = 0; i < list.size(); i++) {
                list.get(i).sort((p1, p2) -> p2.x - p1.x);
            }

            redrawMap();

            for (int i = 0; i < r; i++) {
                Arrays.fill(board[i], '.');
            }

            for (ArrayList<Pair> pairs : list) {
                for (Pair pair : pairs) {
                    board[pair.x][pair.y] = 'x';
                }
            }
        }

        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }

    }

    static void redrawMap() {
        boolean[] check = new boolean[r * c];

        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                int clusterNum = map[i][j];

                if (clusterNum != 0 && !check[clusterNum]) {
                    check[clusterNum] = true;
                    int maxRow = list.get(clusterNum).get(0).x;

                    loop :
                    while (maxRow + 1 < r) {
                        for (Pair pair : list.get(clusterNum)) {
                            if (map[pair.x + 1][pair.y] != clusterNum && board[pair.x + 1][pair.y] == 'x') {
                                break loop;
                            }
                        }
                        for (Pair pair : list.get(clusterNum)) {
                            pair.x += 1;
                        }
                        maxRow += 1;
                    }
                }
            }
        }

    }

    static void throwStick(int row, boolean left) {
        if (left) {
            for (int i = 0; i < c; i++) {
                if (board[row][i] == 'x') {
                    board[row][i] = '.';
                    return;
                }
            }
        } else {
            for (int i = c - 1; i >= 0; i--) {
                if (board[row][i] == 'x') {
                    board[row][i] = '.';
                    return;
                }
            }
        }
    }

    static void markCluster(int x, int y, int clusterNum) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;
        map[x][y] = clusterNum;
        list.get(clusterNum).add(new Pair(x, y));

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (visited[nx][ny] || board[nx][ny] == '.') continue;

                visited[nx][ny] = true;
                map[nx][ny] = clusterNum;
                list.get(clusterNum).add(new Pair(nx, ny));
                q.add(new Pair(nx, ny));
            }
        }
    }

}
