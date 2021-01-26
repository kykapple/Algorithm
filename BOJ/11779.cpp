#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

vector<pair<int, int>> graph[1001];
int d[1001];
int cites[1001];
int n;

void Dijkstra(int start) {

	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	pq.push({ 0, start });

	while (!pq.empty()) {
		int dist = pq.top().first;
		int point = pq.top().second;
		pq.pop();

		if (dist > d[point]) continue;

		for (int i = 0; i < graph[point].size(); i++) {
			if (d[graph[point][i].first] > dist + graph[point][i].second) {
				d[graph[point][i].first] = dist + graph[point][i].second;
				cites[graph[point][i].first] = point;
				pq.push({ d[graph[point][i].first] , graph[point][i].first });
			}
		}
	}

}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n;

	int m;
	cin >> m;

	for (int i = 0; i < m; i++) {
		int u, v, w;
		cin >> u >> v >> w;

		graph[u].push_back({ v,w }); // 도착 정점과 가중치
	}

	int s, e;
	cin >> s >> e;

	fill(d, d + 1001, INF);
	Dijkstra(s);

	cout << d[e] << '\n';

	stack<int> result;
	result.push(e);

	for (int t = result.top(); t != s; t = result.top())
		result.push(cites[t]);	// 최단 경로 스택에 적재

	cout << result.size() << '\n';

	while (!result.empty()) {
		cout << result.top() << " ";
		result.pop();
	}

	return 0;
}