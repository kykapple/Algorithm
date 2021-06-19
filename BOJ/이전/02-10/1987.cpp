#include <bits/stdc++.h>
using namespace std;

int r, c, result;
char graph[20][20];
int alphabet[26];

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

void DFS(int x, int y, int cnt) {
	result = max(result, cnt);

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
		int idx = graph[nx][ny] - 65;
		if (alphabet[idx]) continue;

		alphabet[idx] = 1;
		DFS(nx, ny, cnt + 1);
		alphabet[idx] = 0;

	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> r >> c;
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			cin >> graph[i][j];
		}
	}

	alphabet[graph[0][0] - 65] = 1;
	DFS(0, 0, 1);

	cout << result;

	return 0;
}