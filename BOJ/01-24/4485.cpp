#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int graph[125][125];
int d[125][125];
int n;

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

void Dijkstra() {
	d[0][0] = graph[0][0];

	priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<pair<int, pair<int, int>>>> pq;
	pq.push({ d[0][0], {0,0} });

	while (!pq.empty()) {
		int dist = pq.top().first;
		int x = pq.top().second.first;
		int y = pq.top().second.second;
		pq.pop();

		if (dist > d[x][y]) continue;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			if (d[nx][ny] > dist + graph[nx][ny]) {
				d[nx][ny] = dist + graph[nx][ny];
				pq.push({ d[nx][ny], {nx, ny} });
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int cnt = 0;
	while (true) {
		cnt++;
		cin >> n;

		if (n == 0) break;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> graph[i][j];
				d[i][j] = INF;
			}
		}

		Dijkstra();
		cout << "Problem " << cnt << ": " << d[n - 1][n - 1] << '\n';
	}

	return 0;
}