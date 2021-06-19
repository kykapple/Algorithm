#include <bits/stdc++.h>
using namespace std;

int graph[100][100];
int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };
int m, n, k;

int BFS(int i, int j) {
	int cnt = 0;
	cnt++;
	graph[i][j] = 1;
	
	queue<pair<int, int>> q;
	q.push({ i, j });
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
			if (graph[nx][ny] == 1) continue;
			graph[nx][ny] = 1;
			cnt++;
			q.push({ nx,ny });
		}
	}
	
	return cnt;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> m >> n >> k;

	for (int i = 0; i < k; i++) {
		int x1, y1, x2, y2;
		cin >> x1 >> y1 >> x2 >> y2;

		for (int j = y1; j < y2; j++) {
			for (int k = x1; k < x2; k++) {
				graph[j][k] = 1;
			}
		}
	}

	int cnt = 0;
	vector<int> result;
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (!graph[i][j]) {
				result.push_back(BFS(i, j));
				cnt++;
			}
		}
	}

	cout << cnt << '\n';

	sort(result.begin(), result.end());
	for (int i : result)
		cout << i << " ";

	return 0;
}