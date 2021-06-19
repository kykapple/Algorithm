#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
vector<pair<pair<int, int>, int>> graph;
long long dist[501];
int n, m;

bool bf(int start) {
	dist[start] = 0;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < graph.size(); j++) {
			int from = graph[j].first.first;
			int to = graph[j].first.second;
			int cost = graph[j].second;

			if (dist[from] == INF) continue;
			if (dist[to] > dist[from] + cost) {
				dist[to] = dist[from] + cost;
				if (i == n - 1) return true;
			}
		}
	}
	return false;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;

	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;

		graph.push_back({ {a,b}, c });
	}

	fill_n(dist, 501, INF);
	
	if (bf(1)) 
		cout << -1;
	else {
		for (int i = 2; i <= n; i++) {
			if (dist[i] == INF)
				cout << -1 << '\n';
			else
				cout << dist[i] << '\n';
		}
	}

	return 0;
}
