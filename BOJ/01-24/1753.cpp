#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
const int MAX = 20001;
vector<pair<int, int>> graph[MAX];
int d[MAX];
int v, e;

void Dijkstra(int start) {
	
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	pq.push({ 0, start });
	d[start] = 0;

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

	cin >> v >> e;

	int start;
	cin >> start;

	for (int i = 0; i < e; i++) {
		int u, v, w;
		cin >> u >> v >> w;

		graph[u].push_back({ v,w });
	}

	fill(d, d + MAX, INF);

	Dijkstra(start);

	for (int i = 1; i <= v; i++) {
		if (d[i] == INF)
			cout << "INF" << '\n';
		else
			cout << d[i] << '\n';
	}

	return 0;
}