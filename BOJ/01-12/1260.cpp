#include <bits/stdc++.h>
using namespace std;

int graph[1001][10000];
bool visited[1001];
int n, m, v;

void DFS(int v, int last_idx[]) {
	cout << v << ' ';
	visited[v] = true;

	int i = last_idx[v];
	while (i >= 0) {
		int num = graph[v][i];
		if (!visited[num])
			DFS(num, last_idx);
		i--;
	}
}

void BFS(int v, int last_idx[]) {
	for (int i = 0; i < 1001; i++) visited[i] = false;

	cout << v << ' ';
	visited[v] = true;
	
	queue<int> q;
	q.push(v);

	while (!q.empty()) {
		int val = q.front();
		q.pop();
		int i = last_idx[val];
		while (i >= 0) {
			int num = graph[val][i];
			if (!visited[num]) {
				visited[num] = true;
				cout << num << ' ';
				q.push(num);
			}
			i--;
		}
	}


}

bool compare(int val1, int val2) {
	return val1 > val2;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> n >> m >> v;

	for (int i = 0; i < m; i++) {
		int x, y;
		cin >> x >> y;

		int j = 0;
		while (graph[x][j] != 0)
			j++;
		graph[x][j] = y;

		j = 0;
		while (graph[y][j] != 0)
			j++;
		graph[y][j] = x;
	}
	int* last_idx = new int[n + 1];

	for (int i = 1; i <= n; i++) {
		int j = 0;
		while (graph[i][j])
			j++;
		last_idx[i] = j - 1;
	}


	for (int i = 1; i <= n; i++) {
		sort(graph[i], graph[i] + m, compare);
	}

	DFS(v, last_idx);
	cout << '\n';
	BFS(v, last_idx);

	return 0;
}