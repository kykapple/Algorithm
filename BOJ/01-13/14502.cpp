#include <bits/stdc++.h>
using namespace std;

int graph[8][8];
int temp[8][8];
bool visited[8][8];
int n, m;
int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

int BSF(int x, int y) {
	int cnt = 0;

	if (!visited[x][y]) {
		visited[x][y] = true;
		cnt++;
		queue<pair<int, int>> q;
		q.push({ x,y });

		while (!q.empty()) {
			int x_ = q.front().first;
			int y_ = q.front().second;
			q.pop();

			for (int i = 0; i < 4; i++) {
				int nx = x_ + dx[i];
				int ny = y_ + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (temp[nx][ny] == 1) continue;
				if (temp[nx][ny] == 0 && !visited[nx][ny]) {
					temp[nx][ny] = 2;
					visited[nx][ny] = true;
					q.push({ nx,ny });
					cnt++;
				}
			}
		}
	}
	return cnt;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;

	vector<pair<int, int>> bin;

	int one = 0; int sum = n * m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> graph[i][j];
			temp[i][j] = graph[i][j];
			if (graph[i][j] == 0) {
				bin.push_back({ i,j });
			}
			else if (graph[i][j] == 1)
				one++;
		}
	}

	int min = 64;
	for (int i = 0; i < bin.size() - 2; i++) {
		int x1 = bin[i].first; 
		int y1 = bin[i].second;
		for (int j = i + 1; j < bin.size() - 1; j++) {
			int x2 = bin[j].first;
			int y2 = bin[j].second;
			for (int k = j + 1; k < bin.size(); k++) {
				int x3 = bin[k].first;
				int y3 = bin[k].second;

				temp[x1][y1] = 1; temp[x2][y2] = 1; temp[x3][y3] = 1;
				int val = 0;
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < m; y++) {
						if (temp[x][y] == 2) {
							val += BSF(x, y);
						}
					}
				}

				if (min > val) min = val;
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < m; y++) {
						temp[x][y] = graph[x][y];
						visited[x][y] = false;
					}
				}
			}
		}
	}

	cout << sum - (min + one + 3);

	return 0;
}