#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int graph[50][50];
int d[50][50];
int n;

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

void Dijkstra(int x, int y) {
	d[x][y] = 0;

	// 우선순위 큐를 통해 가중치가 낮은 순으로 저장
	priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<pair<int, pair<int, int>>>> pq;
	pq.push({ 0, {x,y} });

	while (!pq.empty()) {	// 가중치가 낮은 위치부터 탐색하게 됨
		int dist = pq.top().first; // 가중치
		int x = pq.top().second.first; // 행
		int y = pq.top().second.second; // 열
		pq.pop();

		if (dist > d[x][y]) continue;	// 중복 연산 방지

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			if (d[nx][ny] > dist + graph[nx][ny]) {
				if (graph[nx][ny])	
					d[nx][ny] = dist;	// 흰 방이면 가중치를 그대로 사용
				else	
					d[nx][ny] = dist + 1;	// 검은 방이면 +1을 통해 흰 방으로 바꿈
				pq.push({ d[nx][ny], {nx,ny} });
			}
		}
	}
}

int main(void) {
	scanf_s("%d", &n);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf_s("%1d", &graph[i][j]);
			d[i][j] = INF;
		}
	}

	Dijkstra(0, 0);

	cout << d[n - 1][n - 1];

	return 0;
}