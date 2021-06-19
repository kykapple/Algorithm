#include <iostream>
#include <queue>
#include <vector>
#include <string.h>
using namespace std;

const int INF = 1e9;
vector<pair<int,int>> graph[1001];
int dist[1001];
int path[1001];
int n, m;

void Dijkstra() {
    for (int i = 1; i <= n; i++) {
        dist[i] = INF;
        path[i] = 0;
    }

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({ 0, 1 });
    dist[1] = 0;

    while (!pq.empty()) {
        int cost = pq.top().first;
        int point = pq.top().second;
        pq.pop();

        if (dist[point] < cost) continue;

        for (int i = 0; i < graph[point].size(); i++) {
            if (dist[graph[point][i].first] > cost + graph[point][i].second) {
                dist[graph[point][i].first] = cost + graph[point][i].second;
                pq.push({ dist[graph[point][i].first], graph[point][i].first });
                path[graph[point][i].first] = point;
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;

    for (int i = 0; i < m; i++) {
        int a, b, c;
        cin >> a >> b >> c;

        graph[a].push_back({ b, c });
        graph[b].push_back({ a, c });
    }

    memset(dist, INF, 1001);

    Dijkstra();
    
    int cnt = 0;
    for (int i = 1; i <= n; i++) {
        if (path[i])
            cnt++;
    }

    cout << cnt << '\n';

    for (int i = 1; i <= n; i++) {
        if (path[i])
            cout << i << ' ' << path[i] << '\n';
    }

    return 0;
}