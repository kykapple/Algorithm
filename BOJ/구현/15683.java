import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer = Integer.MAX_VALUE;
    static int[][] board;
    static ArrayList<Node> list;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };
    static int[][] move = { { 3 }, { 1, 3 }, { 0, 1 }, { 3, 0, 1 }, { 0, 1, 2, 3 } };

    static class Node {
        int x;
        int y;
        int type;

        public Node(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= board[i][j] && board[i][j] <= 5) {
                    list.add(new Node(i, j, board[i][j] - 1));
                }
            }
        }

        solve(board, 0);
        System.out.println(answer);
    }

    static void solve(int[][] board, int idx) {
        if (idx == list.size()) {
            answer = Math.min(answer, calcArea(board));
            return;
        }

        Node node = list.get(idx);
        int[] dirs = move[node.type];
        for (int i = 0; i < 4; i++) {
            int[][] newBoard = copyBoard(board);
            for (int j = 0; j < dirs.length; j++) {
                dirs[j] = (dirs[j] + 1) % 4;
            }

            checkBoard(newBoard, node, dirs);
            solve(newBoard, idx + 1);
        }
    }

    static int calcArea(int[][] board) {
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    ret += 1;
                }
            }
        }
        return ret;
    }

    static void checkBoard(int[][] board, Node node, int[] dirs) {
        int x = node.x, y = node.y;

        for (int i = 0; i < dirs.length; i++) {
            int idx = dirs[i];
            int nx = x + dx[idx];
            int ny = y + dy[idx];

            while (true) {
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                if (board[nx][ny] == 6) break;
                if (board[nx][ny] == 0) {
                    board[nx][ny] = -1;
                }
                nx += dx[idx];
                ny += dy[idx];
            }
        }
    }

    static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

}
