#include <bits/stdc++.h>
using namespace std;

int graph[1000][1000];
vector<pair<int, int>> idx;
int m, n;

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

void BFS() {
	queue<pair<int, int>> q;

	for (int i = 0; i < idx.size(); i++) {
		int x = idx[i].first;
		int y = idx[i].second;
		q.push({ x, y });
	}

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			if (graph[nx][ny] == 1 || graph[nx][ny] == -1) continue;
			if (graph[nx][ny] == 0) {
				graph[nx][ny] = graph[x][y] + 1;
				q.push({ nx, ny });
			}
		}
	}
}

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> m >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> graph[i][j];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (graph[i][j] == 1) {
				idx.push_back({ i,j });
			}
		}
	}

	BFS();

	bool flag = true;
	int max = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (graph[i][j] == 0)
				flag = false;
			if (max < graph[i][j])
				max = graph[i][j];
		}
	}

	if (flag)
		cout << max - 1;
	else
		cout << -1;

	return 0;
}