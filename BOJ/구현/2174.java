import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k, t;
    static int graph[][];
    // 방향에 대한 인덱스를 저장하기 위한 map
    static Map<Character, Integer> direction = new HashMap<>();
    // n번 로봇의 방향을 인덱스로 저장하기 위한 map
    static Map<Integer, Integer> robot_direction = new HashMap<>();
    // 로봇의 좌표를 저장하기 위한 map
    static Map<Integer, Pair> robot_location = new HashMap<>();
    static int dx[] = {1,0,-1,0}, dy[] = {0,-1,0,1};

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

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graph = new int[n+1][m+1];

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        direction.put('N', 0);
        direction.put('W', 1);
        direction.put('S', 2);
        direction.put('E', 3);

        for(int i=1; i<=k; i++) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            char ch = st.nextToken().charAt(0);

            graph[x][y] = i;    // (x,y)에 i번 로봇 위치
            robot_direction.put(i, direction.get(ch));
            robot_location.put(i, new Pair(x, y));
        }

        boolean flag = true;
        for(int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine());

            int robot = Integer.parseInt(st.nextToken());
            char order = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());

            if(!solve(robot, order, repeat)) {
                flag = false;
                break;
            }
        }

        if(flag)
            System.out.println("OK");
    }

    static boolean solve(int robot, char order, int repeat) {
        int dir = robot_direction.get(robot);

        switch(order) {
            case 'L':
                for(int i=0; i<repeat; i++) {
                    if(dir == 3)
                        dir = 0;
                    else
                        dir += 1;
                }
                robot_direction.put(robot, dir);
                break;
            case 'R':
                for(int i=0; i<repeat; i++) {
                    if(dir == 0)
                        dir = 3;
                    else
                        dir -= 1;
                }
                robot_direction.put(robot, dir);
                break;
            case 'F':
                Pair cur = robot_location.get(robot);
                int nx = cur.x, ny = cur.y;
                for(int i=0; i<repeat; i++) {
                    nx += dx[dir];
                    ny += dy[dir];

                    // 벽에 충돌하는 경우
                    if(nx < 1 || nx > n || ny < 1 || ny > m) {
                        System.out.println("Robot " + robot + " crashes into the wall");
                        return false;

                        // 다른 로봇에 부딪하는 경우
                    } else if(graph[nx][ny] != 0) {
                        System.out.println("Robot " + robot + " crashes into robot " + graph[nx][ny]);
                        return false;
                    }
                }
                graph[cur.x][cur.y] = 0;
                graph[nx][ny] = robot;
                robot_location.put(robot, new Pair(nx, ny));
                break;
        }

        return true;
    }
}