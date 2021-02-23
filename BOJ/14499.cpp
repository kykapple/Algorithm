#include <bits/stdc++.h>
using namespace std;

int n, m, x, y, k, temp;
int graph[20][20];
queue<int> root;
int dice[7];

int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m >> x >> y >> k;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> graph[i][j];
        }
    }

    for (int i = 0; i < k; i++) {
        int r;
        cin >> r;

        root.push(r);
    }

    queue<pair<int, int>> q;
    q.push({ x,y });

    vector<int> result;
    while (!q.empty()) {
        int nx = q.front().first;
        int ny = q.front().second;   // 현재 주사위 좌표 추출
        q.pop();

        int temp_x = nx, temp_y = ny;

        int dir;
        if (!root.empty()) {
            dir = root.front(); // 방향 추출
            root.pop();
        }
        else break;

        switch (dir) {
        case 1: // 동쪽
            ny++;
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
            temp = dice[1];

            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
            break;
        case 2: // 서쪽
            ny--;
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
            temp = dice[1];

            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
            break;
        case 3: // 북쪽
            nx--;
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
            temp = dice[1];

            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
            break;
        case 4: // 남쪽
            nx++;
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
            temp = dice[1];

            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
            break;
        }

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            q.push({ temp_x,temp_y });
        }
        else {
            if (graph[nx][ny]) {
                dice[1] = graph[nx][ny];
                graph[nx][ny] = 0;
            }
            else {
                graph[nx][ny] = dice[1];
            }
            result.push_back(dice[6]);
            q.push({ nx,ny });
        }
    }
        
    for (int i = 0; i < result.size(); i++)
        cout << result[i] << '\n';

    return 0;
}