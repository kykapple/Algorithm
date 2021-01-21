#include <bits/stdc++.h>
using namespace std;
#define MIN 987654321;

int graph[50][50];
vector<pair<int, int>> chicken;
bool visited[13];
int n, m, result = MIN;

void DFS(int idx, int check) {

	if (check == m) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				int length = MIN;
				if (graph[i][j] == 1) {
					for (int k = 0; k < 13; k++) {
						if (visited[k]) {
							int cx = chicken[k].first;
							int cy = chicken[k].second;

							length = min(length, abs(i - cx) + abs(j - cy));
						}
					}
					sum += length;
				}
			}
		}

		result = min(result, sum);
		return;
	}

	for (int i = idx; i < chicken.size(); i++) {
		visited[i] = true;
		DFS(i + 1, check + 1);
		visited[i] = false;
		DFS(i + 1, check);
	}
	
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> graph[i][j];

			if (graph[i][j] == 2)
				chicken.push_back({ i,j });
		}
	}

	DFS(0, 0);

	cout << result;

	return 0;
}