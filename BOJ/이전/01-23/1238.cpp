#include <bits/stdc++.h>
using namespace std;

int INF = 1000000;
int graph[1001][1001];
int graph_reverse[1001][1001];
bool visited[1001];
int dist[1001];
int dist_reverse[1001];
int n, m, x;

int choose(int start, int x) {
	int idx = 0, min = INF;

	if (x == 1) {
		for (int i = 1; i <= n; i++) {
			if (dist[i] < min && !visited[i]) {
				min = dist[i];
				idx = i;
			}
		}
	}
	else {
		for (int i = 1; i <= n; i++) {
			if (dist_reverse[i] < min && !visited[i]) {
				min = dist_reverse[i];
				idx = i;
			}
		}
	}
	return idx;
}

void dijkstra(int start, int x) {
	for (int i = 1; i <= n; i++) {
		if (x == 1)
			dist[i] = graph[start][i];
		else
			dist_reverse[i] = graph_reverse[start][i];
		visited[i] = false;
	}

	visited[start] = true;
	for (int i = 0; i < n - 2; i++) {
		int v = choose(start ,x);

		visited[v] = true;

		if (x == 1) {
			for (int j = 1; j <= n; j++) {
				if (dist[j] > dist[v] + graph[v][j])
					dist[j] = dist[v] + graph[v][j];
			}
		}
		else {
			for (int j = 1; j <= n; j++) {
				if (dist_reverse[j] > dist_reverse[v] + graph_reverse[v][j])
					dist_reverse[j] = dist_reverse[v] + graph_reverse[v][j];
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m >> x;

	for (int i = 0; i <= n; i++) {
		for (int j = 0; j <= n; j++) {
			graph[i][j] = INF;
			graph_reverse[i][j] = INF;
		}
		graph[i][i] = 0;
		graph_reverse[i][i] = 0;
	}
	
	for (int i = 0; i < m; i++) {
		int u, v, w;
		cin >> u >> v >> w;

		graph[u][v] = w;
		graph_reverse[v][u] = w;
	}

	dijkstra(x, 1);
	dijkstra(x, 2);

	int result = 0;
	for (int i = 1; i <= n; i++) 
		result = max(result, dist[i] + dist_reverse[i]);
	
	cout << result;

	return 0;
}