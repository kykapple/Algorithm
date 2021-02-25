#include <iostream>
using namespace std;

int graph[500][500];
int dp[500][500];
bool visited[500][500];
int n, m;

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

int DFS(int x, int y) {
    if (visited[x][y]) return dp[x][y];
    else if (x == n - 1 && y == m - 1) return 1;

    visited[x][y] = true;
    int temp = 0;
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

        if (graph[x][y] > graph[nx][ny]) {
            temp += DFS(nx, ny);
        }
    }

    return dp[x][y] = temp;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> graph[i][j];
        }
    }

    cout << DFS(0, 0);

    return 0;
}
