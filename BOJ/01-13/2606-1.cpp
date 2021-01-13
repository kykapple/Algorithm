#include <bits/stdc++.h>
using namespace std;

int graph[101][101];
bool visited[101];
int n, cnt;

void DFS(int i) {
	visited[i] = true;

	for (int j = 1; j <= n; j++) {
		if (graph[i][j] && !visited[j]) {
			DFS(j);
			cnt++;
		}
	}
}

void BFS(int i) {
	visited[i] = true;

	queue<int> q;
	q.push(i);
	while (!q.empty()) {
		int val = q.front();
		q.pop();

		for (int j = 1; j <= n; j++) {
			if (graph[val][j] && !visited[j]) {
				visited[j] = true;
				q.push(j);
				cnt++;
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n;

	int num;
	cin >> num;

	for (int i = 0; i < num; i++) {
		int u, v;
		cin >> u >> v;

		graph[u][v] = graph[v][u] = 1;
	}

	BFS(1);
	cout << cnt;

	return 0;
}