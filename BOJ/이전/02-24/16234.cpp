#include <bits/stdc++.h>
using namespace std;

int n, l, r;
int graph[50][50];
bool visited[50][50];
int unity[50][50];
int totalSum[2501];
int totalCnt[2501];

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

bool open_border(int x, int y, int u) {    // 국경선 열기
    queue<pair<int, pair<int, int>>> q;
    q.push({ u,{x,y} });
    unity[x][y] = u;
    visited[x][y] = true;
    int total = graph[x][y];
    int cnt = 1;

    bool flag = false;  // 인구 이동이 있는지 체크

    while (!q.empty()) {
        int unit = q.front().first;
        int x = q.front().second.first;
        int y = q.front().second.second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (visited[nx][ny]) continue;

            int diff = abs(graph[x][y] - graph[nx][ny]);
            
            if (l <= diff && diff <= r) {
                visited[nx][ny] = true;
                unity[nx][ny] = unit;
                q.push({ unit, {nx,ny} });
                flag = true;
                total += graph[nx][ny];
                cnt++;
            }
        }
    }

    totalSum[u] = total;
    totalCnt[u] = cnt;
    return flag;
}

void move(int max_unit) {
    // 인구 이동
    for (int k = 1; k <= max_unit; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (unity[i][j] == k) {
                    graph[i][j] = totalSum[k] / totalCnt[k];
                }
            }
        }
    }
}

int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> l >> r;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> graph[i][j];
        }
    }

    int cnt = 0;
    while (true) {
        // 국경선 열기 + 인구 이동
        int u = 0;
        bool flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // 시간 절약 부분 - 주변에 연합을 만들 수 있는 나라일 때만 open_border호출
                bool bfs = false;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    int diff = abs(graph[i][j] - graph[nx][ny]);
                    if (!unity[nx][ny] && (l <= diff && diff <= r)) {
                        bfs = true;
                        break;
                    }
                }
                // 시간 절약 부분

                if (bfs) {
                    u++;
                    if (open_border(i, j, u)) {
                        flag = true;
                    }
                }
            }
        }

        if (!flag) break;
        move(u);
        cnt++;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
                unity[i][j] = 0;
            }
        }
        memset(totalSum, 0, sizeof(int) * 2501);
        memset(totalCnt, 0, sizeof(int) * 2501);
    }

    cout << cnt;

    return 0;
}