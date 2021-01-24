#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

vector<pair<int, int>> graph[801];
int d[801];
int n, e;

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
				d[graph[point][i].first] = d[point] + graph[point][i].second;
				pq.push({ d[graph[point][i].first], graph[point][i].first });
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	fill(d, d + 801, INF);

	cin >> n >> e;

	for (int i = 0; i < e; i++) {
		int a, b, c;
		cin >> a >> b >> c;

		graph[a].push_back({ b,c });
		graph[b].push_back({ a,c });
	}

	int v1, v2;
	cin >> v1 >> v2;
	
	Dijkstra(v1);
	int v1_v2 = d[v2];
	int v1_n = d[n];

	fill(d, d + n + 1, INF);

	Dijkstra(v2);
	int v2_n = d[n];

	fill(d, d + n + 1, INF);

	Dijkstra(1);
	int start_v1 = d[v1];
	int start_v2 = d[v2];

	long long first = (long long)start_v1 + v1_v2 + v2_n;
	long long second = (long long)start_v2 + v1_v2 + v1_n;

	long long result = min(first, second);

	if (result >= INF)
		cout << -1;
	else
		cout << result;

	return 0;
}
