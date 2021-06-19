#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int n, m;
vector<pair<int, int>> graph[1001];
int d[1001];

void Dijkstra(int start) {

	d[start] = 0;
	priority_queue < pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	pq.push({ 0, start });

	while (!pq.empty()) {
		int dist = pq.top().first;
		int point = pq.top().second;
		pq.pop();

		if (d[point] < dist) continue;

		for (int i = 0; i < graph[point].size(); i++) {
			if (d[graph[point][i].first] > dist + graph[point][i].second) {
				d[graph[point][i].first] = dist + graph[point][i].second;
				pq.push({ d[graph[point][i].first], graph[point][i].first });
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n;
	cin >> m;

	for (int i = 0; i < m; i++) {
		int u, v, w;
		cin >> u >> v >> w;

		graph[u].push_back({ v,w });
	}

	int start, end;
	cin >> start >> end;

	fill(d, d + n + 1, INF);

	Dijkstra(start);

	cout << d[end];

	return 0;
}