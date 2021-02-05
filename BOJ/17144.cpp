#include <bits/stdc++.h>
using namespace std;

int r, c, t;
int house[50][50];
int top, bottom;	// 공기청정기 상, 하단
vector<int> cleaner;
queue<pair<int, pair<int, int>>> location;

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

void run_cleaner() {
	for (int i = top - 1; i > 0; i--)
		house[i][0] = house[i - 1][0];

	for (int j = 0; j < c - 1; j++)
		house[0][j] = house[0][j + 1];

	for (int i = 0; i < top; i++)
		house[i][c - 1] = house[i + 1][c - 1];

	for (int j = c - 1; j > 1; j--) 
		house[top][j] = house[top][j - 1];
	
	house[top][1] = 0;

	for (int i = bottom + 1; i < r - 1; i++)
		house[i][0] = house[i + 1][0];

	for (int j = 0; j < c - 1; j++)
		house[r - 1][j] = house[r - 1][j + 1];
	
	for (int i = r - 1; i > bottom; i--)
		house[i][c - 1] = house[i - 1][c - 1];

	for (int j = c - 1; j > 1; j--)
		house[bottom][j] = house[bottom][j - 1];

	house[bottom][1] = 0;
}

void diffusion() {
	while (!location.empty()) {
		int amount = location.front().first;
		int x = location.front().second.first;
		int y = location.front().second.second;
		location.pop();

		int cnt = 0, temp = amount / 5;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
			if (house[nx][ny] == -1) continue;
			house[nx][ny] += temp;
			cnt++;
		}
		house[x][y] -= (temp * cnt);
	}
}

void check() {
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			if (house[i][j] && house[i][j] != -1)
				location.push({ house[i][j], { i,j } });
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> r >> c >> t;

	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			cin >> house[i][j];

			if (house[i][j] == -1)
				cleaner.push_back(i);
			else if (house[i][j])
				location.push({ house[i][j], { i,j } });
		}
	}

	top = cleaner[0];
	bottom = cleaner[1];

	for (int i = 0; i < t; i++) {
		diffusion();
		run_cleaner();
		check();
	}

	int ans = 0;
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			if (house[i][j] && house[i][j] != -1)
				ans += house[i][j];
		}
	}

	cout << ans;

	return 0;
}