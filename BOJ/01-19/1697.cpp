#include <bits/stdc++.h>
using namespace std;

int graph[100001];
bool visited[100001];

void BSF(int start) {
	visited[start] = true;

	queue<int> q;
	q.push(start);

	while (!q.empty()) {
		int val = q.front();
		q.pop();

		int dx[] = { -1, 1, val };

		for (int i = 0; i < 3; i++) {
			int nx = val + dx[i];

			if (nx < 0 || nx > 100000) continue;
			if (!graph[nx] && !visited[nx]) {
				graph[nx] = graph[val] + 1;
				q.push(nx);
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n, k;
	cin >> n >> k;

	BSF(n);

	cout << graph[k];

	return 0;
}