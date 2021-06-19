#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int graph[100][100];
int d[100][100];
int m, n;

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

void Dijkstra(int x, int y) {
	d[0][0] = 0;

	priority_queue < pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<pair<int, pair<int, int>>>> pq;
	pq.push({ 0, { x,y } });

	while (!pq.empty()) {
		int dist = pq.top().first;
		int cur_x = pq.top().second.first;
		int cur_y = pq.top().second.second;
		pq.pop();

		if (d[cur_x][cur_y] < dist) continue;

		for (int i = 0; i < 4; i++) {
			int nx = cur_x + dx[i];
			int ny = cur_y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			if (d[nx][ny] > d[cur_x][cur_y] + graph[nx][ny]) {
				d[nx][ny] = d[cur_x][cur_y] + graph[nx][ny];
				pq.push({ d[nx][ny], { nx,ny } });
			}
		}
	}

}

int main(void) {
	cin >> m >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf_s("%1d", &graph[i][j]);
			d[i][j] = INF;
		}
	}

	Dijkstra(0, 0);

	cout << d[n - 1][m - 1];

	return 0;
}