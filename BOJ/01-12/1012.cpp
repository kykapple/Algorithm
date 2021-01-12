#include <bits/stdc++.h>
using namespace std;

int graph[50][50];
int m, n, k;

void DFS(int j, int k) {
	if (j < 0 || j >= n || k < 0 || k >= m)
		return;

	if (graph[j][k] == 1) {
		graph[j][k] = 0;

		DFS(j + 1, k);
		DFS(j - 1, k);
		DFS(j, k - 1);
		DFS(j, k + 1);
	}
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int t;
	cin >> t;

	vector<int> result;
	for (int i = 0; i < t; i++) {
		cin >> m >> n >> k;

		for (int j = 0; j < k; j++) {
			int x, y;
			cin >> x >> y;

			graph[y][x] = 1;
		}

		int cnt = 0;
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				if (graph[j][k]) {
					DFS(j, k);
					cnt++;
				}
			}
		}

		result.push_back(cnt);
	}

	for (int i : result)
		cout << i << '\n';

	return 0;
}
