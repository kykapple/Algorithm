#include <iostream>
#include <algorithm>
using namespace std;
#define MAX 1e9

int n;
int graph[16][16];
int dp[1 << 16][16];

int solve(int visited, int cur) {
    if (visited  == ((1 << n) - 1)) return graph[cur][0];    // 모든 정점을 다 방문한 경우 -> 출발지까지의 거리 반환

    if (dp[visited][cur]) return dp[visited][cur];

    dp[visited][cur] = MAX;
    for (int i = 0; i < n; i++) {
        if (visited & 1 << i) continue; // 이미 방문한 도시인 경우
        if (!graph[cur][i]) continue;   // 해당 정점으로 갈 수 없는 경우

        dp[visited][cur] = min(dp[visited][cur], solve(visited + (1 << i), i) + graph[cur][i]);
    }

    return dp[visited][cur];

}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> graph[i][j];

            if (i != j && !graph[i][j])
                graph[i][j] = MAX;
        }
    }

    cout << solve(1, 0);

    return 0;
}