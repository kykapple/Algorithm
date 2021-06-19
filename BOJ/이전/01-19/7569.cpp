#include <bits/stdc++.h>
using namespace std;

typedef struct {
	int i;
	int j;
	int k;
} values;

int tomato[100][100][100];
bool visited[100][100][100];
bool flag;
int m, n, h;
queue<values> q;

int dx[] = { 0,0,0,0,-1,1 };
int dy[] = { 0,0,-1,1,0,0 };
int dz[] = { 1,-1,0,0,0,0 };

void BSF() { 
	while (!q.empty()) {
		int z = q.front().i;
		int x = q.front().j;
		int y = q.front().k;
		q.pop();

		for (int i = 0; i < 6; i++) {
			int nz = z + dz[i];
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m || nz < 0 || nz >= h) continue;
			if (tomato[nz][nx][ny] == -1 || tomato[nz][nx][ny] == 1) continue;
			if (tomato[nz][nx][ny] == 0 && !visited[nz][nx][ny]) {
				visited[nz][nx][ny] = true;
				tomato[nz][nx][ny] = tomato[z][x][y] + 1;
				q.push({ nz,nx,ny });
				flag = true;
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> m >> n >> h;

	for (int i = 0; i < h; i++) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				cin >> tomato[i][j][k];
			}
		}
	}

	for (int i = 0; i < h; i++) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				if (tomato[i][j][k] == 1) {
					q.push({ i,j,k });
					visited[i][j][k] = true;
				}
			}
		}
	}

	BSF();

	if (!flag)
		cout << 0;
	else {
		int days = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if (tomato[i][j][k] == 0) {
						cout << -1;
						return 0;
					}
					days = max(days, tomato[i][j][k]);
				}
			}
		}

		cout << days - 1;
	}

	return 0;
}
