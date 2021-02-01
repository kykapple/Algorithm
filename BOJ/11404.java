#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int n, m;
int graph[101][101];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			graph[i][j] = INF;
		}
		graph[i][i] = 0;
	}

	for (int i = 0; i < m; i++) {
		int u, v, w;
		cin >> u >> v >> w;

		if (graph[u][v] != INF) {
			if (graph[u][v] > w)
				graph[u][v] = w;
		}
		else
			graph[u][v] = w;
	}

	for (int k = 1; k <= n; k++) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (graph[i][j] > graph[i][k] + graph[k][j])
					graph[i][j] = graph[i][k] + graph[k][j];
			}
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (graph[i][j] == INF)
				cout << 0 << ' ';
			else
				cout << graph[i][j] << ' ';
		}
		cout << endl;
	}

	return 0;
}