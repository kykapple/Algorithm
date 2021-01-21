#include <bits/stdc++.h>
using namespace std;

int graph[500][500];
int n, m;

int c1[] = { 1,2,3 }; // 일자 테트로미노

int c2_x[] = { 0,1,1 };
int c2_y[] = { 1,1,0 }; // 사각형 테트로미노

int c3_x[] = { 0,-1,-2 };
int c3_y[] = { -1,-1,-1 }; 
int c4_x[] = { -1,-1,-1 };
int c4_y[] = { 0,1,2 }; 
int c5_x[] = { 0,1,2 };
int c5_y[] = { -1,-1,-1 }; // ㄴ자 테트로미노 

int c6_x[] = { 1,1,2 };
int c6_y[] = { 0,1,1 }; // 4번째 테트로미노

int c7_x[] = { -1,-1,-1 };
int c7_y[] = { 0,1,-1 }; // 5번째 테트로미노

int BFS(int x, int y) {
	int max_val = 0, sum = graph[x][y];

	//case 1-1
	for (int i = 0; i < 3; i++) {
		int nx = x + c1[i];
		int ny = y;

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = sum;
	sum = graph[x][y];

	//case 1-2
	for (int i = 0; i < 3; i++) {
		int nx = x;
		int ny = y + c1[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 2
	for (int i = 0; i < 3; i++) {
		int nx = x + c2_x[i];
		int ny = y + c2_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 3-1
	for (int i = 0; i < 3; i++) {
		int nx = x + c3_x[i];
		int ny = y + c3_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 3-2
	for (int i = 0; i < 3; i++) {
		int nx = x - c3_x[i];
		int ny = y - c3_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 3-3
	for (int i = 0; i < 3; i++) {
		int nx = x + c4_x[i];
		int ny = y + c4_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 3-4
	for (int i = 0; i < 3; i++) {
		int nx = x - c4_x[i];
		int ny = y - c4_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 3-5
	for (int i = 0; i < 3; i++) {
		int nx = x + c5_x[i];
		int ny = y + c5_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 3-6
	for (int i = 0; i < 3; i++) {
		int nx = x - c5_x[i];
		int ny = y - c5_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 3-7
	for (int i = 0; i < 3; i++) {
		int nx = x + c4_x[i];
		int ny = y - c4_y[i];
	
		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 3-8
	for (int i = 0; i < 3; i++) {
		int nx = x - c4_x[i];
		int ny = y + c4_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 4-1
	for (int i = 0; i < 3; i++) {
		int nx = x + c6_x[i];
		int ny = y + c6_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 4-2
	for (int i = 0; i < 3; i++) {
		int nx = x + c6_x[i];
		int ny = y - c6_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 4-3
	for (int i = 0; i < 3; i++) {
		int nx = x - c6_y[i];
		int ny = y + c6_x[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 4-4
	for (int i = 0; i < 3; i++) {
		int nx = x + c6_y[i];
		int ny = y + c6_x[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 5-1
	for (int i = 0; i < 3; i++) {
		int nx = x + c7_x[i];
		int ny = y + c7_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 5-2
	for (int i = 0; i < 3; i++) {
		int nx = x - c7_x[i];
		int ny = y - c7_y[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 5-3
	for (int i = 0; i < 3; i++) {
		int nx = x + c7_y[i];
		int ny = y + c7_x[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	//case 5-4
	for (int i = 0; i < 3; i++) {
		int nx = x - c7_y[i];
		int ny = y - c7_x[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

		sum += graph[nx][ny];
	}
	max_val = max(max_val, sum);
	sum = graph[x][y];

	return max_val;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> graph[i][j];
		}
	}

	int max_val = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			max_val = max(max_val, BSF(i, j));
		}
	}

	cout << max_val;

	return 0;
}
