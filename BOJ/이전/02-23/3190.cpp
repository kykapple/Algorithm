#include <bits/stdc++.h>
using namespace std;

int n, k, l;
bool graph[101][101];
bool apples[101][101];
queue<pair<int, char>> dir;

int dx[] = { 0,1,0,-1 };
int dy[] = { 1,0,-1,0 };

int solve() {
    queue <pair<int, pair<int, int>>> q;
    deque<pair<int, int>> dq;
    q.push({ 0,{1,1} });
    dq.push_back({ 1,1 });
    graph[1][1] = true;

    int idx = 0;
    while (!q.empty()) {
        int time = q.front().first;
        int x = q.front().second.first;
        int y = q.front().second.second;
        q.pop();

        int nx = x + dx[idx];
        int ny = y + dy[idx];
        time++;             // 이동 시 시간 증가

        // 자기자신의 몸 혹은 벽과 부딪히면 게임 끝
        if (graph[nx][ny] || nx < 1 || nx > n || ny < 1 || ny > n) {
            return time;
        }

        dq.push_front({ nx,ny });   // 머리 이동
        
        if (apples[nx][ny]) {   // 사과가 있다면
            graph[nx][ny] = true;
            apples[nx][ny] = false;
        }
        else {  // 사과가 없다면
            graph[nx][ny] = true;
            int tail_x = dq.back().first;
            int tail_y = dq.back().second;
            dq.pop_back();
            graph[tail_x][tail_y] = false;  // 몸길이 변화x
        }

        if (!dir.empty()) {
            if (time == dir.front().first) {  // 방향을 바꿔야 하는 시간이면
                char ch = dir.front().second;
                if (ch == 'D')
                    idx = (idx + 1) % 4;
                else
                    idx = (idx + 3) % 4;

                dir.pop();
            }
        }
        
        q.push({ time,{nx,ny} });
    }
}

int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    cin >> k;

    for (int i = 0; i < k; i++) {
        int x, y;
        cin >> x >> y;

        apples[x][y] = true;
    }

    cin >> l;
    for (int i = 0; i < l; i++) {
        int x;
        char c;
        cin >> x >> c;

        dir.push({ x,c });
    }

    cout << solve();

    return 0;
}
