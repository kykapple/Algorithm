#include <bits/stdc++.h>
using namespace std;

int n;
int house[16][16];
int value[16][16];

int dx[] = { 0,1,1 };
int dy[] = { 1,1,0 };

int cnt;

void BFS() {

	queue<pair<int, pair<int, int>>> q;
	q.push({ 0, { 0,1 } });

	while (!q.empty()) {
		int dir = q.front().first;
		int x = q.front().second.first;
		int y = q.front().second.second;
		q.pop();

		if (x == n - 1 && y == n - 1) {
			cnt++;
			continue;
		}

		switch (dir) {
		case 0:
			for (int i = 0; i < 2; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if (i == 1 && (house[nx - 1][ny] || house[nx][ny - 1])) continue;
				if(!house[nx][ny])
					q.push({ i,{nx,ny} });
			}
			break;
		case 1:
			for (int i = 0; i < 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if (i == 1 && (house[nx - 1][ny] || house[nx][ny - 1])) continue;
				if (!house[nx][ny])
					q.push({ i,{nx,ny} });
			}
			break;
		case 2:
			for (int i = 1; i < 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if (i == 1 && (house[nx - 1][ny] || house[nx][ny - 1])) continue;
				if (!house[nx][ny])
					q.push({ i,{nx,ny} });
			}
			break;
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> house[i][j];
		}
	}

	BFS();

	cout << cnt;

	return 0;
}