#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
vector<pair<pair<int, int>, int>> edge;
int dist[501];
int n, m, w;

bool bf(int start) {
	dist[start] = 0;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < edge.size(); j++) {
			int from = edge[j].first.first;
			int to = edge[j].first.second;
			int cost = edge[j].second;

			//if (dist[from] == INF) continue;
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

	int tc;
	cin >> tc;

	while(tc--) {
		cin >> n >> m >> w;

		fill_n(dist, 501, INF);

		for (int i = 0; i < m; i++) {
			int u, v, w;
			cin >> u >> v >> w;

			edge.push_back({ { u,v }, w });
			edge.push_back({ { v,u }, w });
		}

		for (int i = 0; i < w; i++) {
			int u, v, w;
			cin >> u >> v >> w;

			edge.push_back({ {u,v}, -w });
		}

		if (bf(1))
			cout << "YES" << '\n';
		else
			cout << "NO" << '\n';

		edge.clear();
	}

	return 0;
}