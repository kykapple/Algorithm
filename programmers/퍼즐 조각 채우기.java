import java.util.*;

class Solution {
    int n, answer;
    int[][] game_board, table;
    boolean[][] visited;
    boolean[] check;
    ArrayList<ArrayList<Pair>> list, eList;
    ArrayList<int[][]> puzzleList, emptyList;
    Map<Integer, Integer> puzzleSize;
    int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };
    
    class Pair {
        int x;
        int y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        this.game_board = game_board;
        this.table = table;
        visited = new boolean[n][n];
        list = new ArrayList<>();
        eList = new ArrayList<>();
        emptyList = new ArrayList<>();
        puzzleList = new ArrayList<>();
        puzzleSize = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && table[i][j] == 1) {
                    ArrayList<Pair> puzzle = new ArrayList<>();
                    list.add(puzzle);
                    findPuzzle(i, j, puzzle);
                    puzzleSize.put(list.size() - 1, puzzle.size());
                }
            }
        }
        
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && game_board[i][j] == 0) {
                    ArrayList<Pair> empty = new ArrayList<>();
                    eList.add(empty);
                    findEmptyArea(i, j, empty);
                }
            }
        }
        
        makePuzzle(puzzleList, list);
        makePuzzle(emptyList, eList);
        
        check = new boolean[emptyList.size()];
        solve(0, 0);
        
        return answer;
    }
    
    public void solve(int idx, int sum) {
        if (idx == puzzleList.size()) {
            answer = Math.max(answer, sum);
            return;
        }
        
        boolean flag = true;
        int[][] puzzle = puzzleList.get(idx);
        
        outerLoop:
        for (int i = 0; i < emptyList.size(); i++) {
            if (check[i]) continue;
            int[][] empty = emptyList.get(i);
            
            for (int j = 0; j < 4; j++) {
                puzzle = rotate(puzzle);
                flag = true;
                
                if ((empty.length != puzzle.length) || (empty[0].length != puzzle[0].length)) {
                    flag = false;
                    continue;
                }
                
                loop:
                for (int k = 0; k < puzzle.length; k++) {
                    for (int t = 0; t < puzzle[k].length; t++) {
                        if (puzzle[k][t] != empty[k][t]) {
                            flag = false;
                            break loop;
                        }
                    }
                }
                
                if (flag) {
                    check[i] = true;
                    solve(idx + 1, sum + puzzleSize.get(idx));
                    break outerLoop;
                }
            }
        }
        
        if (!flag) {
            solve(idx + 1, sum);
        }
        
    }
    
    public void makePuzzle(ArrayList<int[][]> pList, ArrayList<ArrayList<Pair>> list) {
        for (ArrayList<Pair> puzzle : list) {
            int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0;
            
            for (Pair p : puzzle) {
                if (minX > p.x) minX = p.x;
                if (minY > p.y) minY = p.y;
                if (maxX < p.x) maxX = p.x;
                if (maxY < p.y) maxY = p.y;
            }
            
            int[][] arr = new int[maxX - minX + 1][maxY - minY + 1];
            
            for (Pair p : puzzle) {
                int x = p.x - minX;
                int y = p.y - minY;
                arr[x][y] = 1;
            }
            pList.add(arr);
        }
    }
    
    public int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] new_arr = new int[m][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                new_arr[j][n-i-1] = arr[i][j];
            }
        }

        return new_arr;
    }
    
    public void findEmptyArea(int x, int y, ArrayList<Pair> empty) {
        visited[x][y] = true;
        empty.add(new Pair(x, y));
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (game_board[nx][ny] == 1 || visited[nx][ny]) continue;
            
            findEmptyArea(nx, ny, empty);
        }  
    }
    
    public void findPuzzle(int x, int y, ArrayList<Pair> puzzle) {
        visited[x][y] = true;
        puzzle.add(new Pair(x, y));
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (table[nx][ny] == 0 || visited[nx][ny]) continue;
            
            findPuzzle(nx, ny, puzzle);
        }   
    }
    
}
