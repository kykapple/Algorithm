#include <bits/stdc++.h>
using namespace std;

int n, m;
int graph[1000][1000];
int visited[1000][1000][2];

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

int BFS() {

	queue<pair<int, pair<int, int>>> q;
	q.push({ 1,{ 0,0} });
	visited[0][0][1] = 1;

	while (!q.empty()) {
		int block = q.front().first;
		int x = q.front().second.first;
		int y = q.front().second.second;
		q.pop();
			
		if (x == n - 1 && y == m - 1)
			return visited[x][y][block];

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			if (visited[nx][ny][block]) continue;
			if (graph[nx][ny] == 1 && block) {
				visited[nx][ny][block - 1] = visited[x][y][block] + 1;
				q.push({ block - 1, { nx, ny} });
			}
			else if (graph[nx][ny] == 0) {
				visited[nx][ny][block] = visited[x][y][block] + 1;
				q.push({ block,{nx,ny} });
			}
		}
	}

	return -1;
}

int main(void) {
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf_s("%1d", &graph[i][j]);
		}
	}

	cout<< BFS();

	return 0;
}