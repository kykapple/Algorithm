#include <bits/stdc++.h>
using namespace std;

int graph[1001][1001];
bool visited[1001];
int n, m;

void DFS(int i) {
	visited[i] = true;

	for (int j = 1; j <= n; j++) {
		if (graph[i][j] && !visited[j]) {
			visited[j] = true;
			DFS(j);
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;

	for (int i = 0; i < m; i++) {
		int u, v;
		cin >> u >> v;

		graph[u][v] = graph[v][u] = 1;
	}

	int cnt = 0;
	for (int i = 1; i <= n; i++) {
		if (!visited[i]) {
			DFS(i);
			cnt++;
		}
	}

	cout << cnt;

	return 0;
}