import java.io.*;
import java.util.*;

public class Main {
    static int n, k, l, ans, dir = 3;
    static int map[][];
    static Queue<Info> q = new LinkedList<>();
    static Deque<Pair> snake = new ArrayDeque<>();
    static int dx[] = {-1,0,1,0}, dy[] = {0,-1,0,1};

    static class Info {
        int sec;
        char ch;

        public Info(int sec, char ch) {
            this.sec = sec;
            this.ch = ch;
        }
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        map[1][1] = 1;                              // 1: 뱀을 의미
        snake.addFirst(new Pair(1, 1));

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 2;                          // 2: 사과를 의미
        }

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        for(int i=0; i<l; i++) {
            st = new StringTokenizer(br.readLine());

            int sec = Integer.parseInt(st.nextToken());
            char ch = st.nextToken().charAt(0);

            q.add(new Info(sec, ch));
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Info info = q.poll();

        while(true) {
            if(!move()) return;

            if(ans == info.sec) {
                dir = turn(info.ch, dir);

                if(!q.isEmpty())
                    info = q.poll();
            }
        }
    }

    static int turn(char ch, int dir) {
        if(ch == 'L') return (dir + 1) % 4;
        else return dir == 0 ? 3 : dir-1;
    }

    static boolean move() {
        Pair p = snake.peekFirst();    // 머리 좌표

        int nx = p.x + dx[dir];
        int ny = p.y + dy[dir];
        ans++;

        if(nx < 1 || nx > n || ny < 1 || ny > n) return false;
        if(map[nx][ny] == 1) return false;

        snake.addFirst(new Pair(nx ,ny));

        // 이동한 칸에 사과가 없을 경우
        if(map[nx][ny] == 0) {
            Pair tail = snake.pollLast();
            map[tail.x][tail.y] = 0;
        }

        map[nx][ny] = 1;

        return true;
    }

}