#include <bits/stdc++.h>
using namespace std;

int n, m;
int graph[100][100];
int air[100][100];
queue<pair<int, int>> temp;

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

void BFS() {

	while (!temp.empty()) {
		int x = temp.front().first;
		int y = temp.front().second;
		temp.pop();

		int around = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			if (air[nx][ny]) {
				around++;
				if (around == 2)
					graph[x][y] = 0;
			}
		}
	}
}

void check_air() {

	queue<pair<int, int>> q;
	q.push({ 0,0 });
	air[0][0] = 1;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			if (graph[nx][ny] || air[nx][ny]) continue;
			air[nx][ny] = 1;
			q.push({ nx,ny });
		}
	}
}

bool is_melt() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++)
			if (graph[i][j] == 1) return false;
	}
	return true;
}

void reset() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (graph[i][j])
				temp.push({ i,j });
			air[i][j] = 0;
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> graph[i][j];

			if(graph[i][j])
				temp.push({ i,j });
		}
	}

	int hours = 0;
	while (true) {
		if (is_melt()) break;
		check_air();
		BFS();
		hours++;
		reset();
	}

	cout << hours;

	return 0;
}
