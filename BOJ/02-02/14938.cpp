#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

int n, m, r;
vector<pair<int, int>> graph[101];
int items[101];
int dist[101];

void Dijkstra(int start) {

	dist[start] = 0;
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	pq.push({ 0, start });

	while (!pq.empty()) {
		int cost = pq.top().first;
		int x = pq.top().second;
		pq.pop();
		
		if (dist[x] < cost) continue;

		for (int i = 0; i < graph[x].size(); i++) {
			if (dist[graph[x][i].first] > cost + graph[x][i].second) {
				dist[graph[x][i].first] = cost + graph[x][i].second;
				pq.push({ dist[graph[x][i].first], graph[x][i].first });
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m >> r;

	for (int i = 1; i <= n; i++) {
		cin >> items[i];
	}

	for (int i = 0; i < r; i++) {
		int a, b, l;
		cin >> a >> b >> l;

		graph[a].push_back({ b, l });
		graph[b].push_back({ a, l });
	}

	int ans = 0;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++)
			dist[j] = INF;

		Dijkstra(i);

		int temp = 0;
		for (int j = 1; j <= n; j++) 
			if (dist[j] <= m) temp += items[j];

		ans = max(ans, temp);
	}

	cout << ans;

	return 0;
}
