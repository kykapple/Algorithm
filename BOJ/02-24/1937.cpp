#include <bits/stdc++.h>
using namespace std;

int n;
int graph[500][500];
int dp[500][500];

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

int DFS(int x, int y) {
    if (dp[x][y]) return dp[x][y];

    dp[x][y] = 1;
    int temp = 1;
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

        if (graph[nx][ny] > graph[x][y]) {
            dp[x][y] = max(dp[x][y], temp + DFS(nx, ny));
        }
    }

    return dp[x][y];
}

int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> graph[i][j];
        }
    }

    int ans = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (!dp[i][j])
                ans = max(ans, DFS(i, j));
            else
                ans = max(ans, dp[i][j]);
        }
    }

    cout << ans;

    return 0;
}