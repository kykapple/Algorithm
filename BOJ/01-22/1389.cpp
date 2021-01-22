#include <bits/stdc++.h>
using namespace std;
#define INF 10000000;

int graph[101][101];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	
	int n, m;
	cin >> n >> m;

	for (int i = 0; i <= n; i++) {
		for (int j = 0; j <= n; j++) {
			graph[i][j] = INF;
		}
		graph[i][i] = 0;
	}

	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;

		graph[a][b] = 1;
		graph[b][a] = 1;
	}

	for (int k = 1; k <= n; k++) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j]);
			}
		}
	}

	int min_idx = 0, min_val = INF;
	for (int i = 1; i <= n; i++) {
		int temp = 0;
		for (int j = 1; j <= n; j++) {
			temp += graph[i][j];
		}
		if (min_val > temp) {
			min_idx = i;
			min_val = temp;
		}
	}

	cout << min_idx;

	return 0;
}