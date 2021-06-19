#include <bits/stdc++.h>
using namespace std;

int n, k;
int graph[100001];
int visited[100001];
int cnt;

void BFS() {

	queue<int> q;
	q.push(n);
	visited[n] = true;

	if (n == k) {
		cnt++;
		return;
	}

	while (!q.empty()) {
		int x = q.front();
		q.pop();

		int dx[] = { -1, 1, x };
		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];

			if (nx < 0 || nx >= 100001) continue;
			if (!visited[nx]) {
				graph[nx] = graph[x] + 1;
				visited[nx] = true;
				q.push(nx);
				if (nx == k) cnt++;
			}
			else {
				if (graph[nx] == graph[x] + 1) {
					if (nx == k)
						cnt++;
					else
						q.push(nx);
				}
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> k;
		
	BFS();
	cout << graph[k] << '\n';
	cout << cnt;

	return 0;
}