#include <bits/stdc++.h>
using namespace std;

int graph[25][25];
bool visited[25][25];
int house_cnt;
int n;
int dan = 0;

void DFS(int i, int j) {
	if (i < 0 || i >= n || j < 0 || j >= n)
		return;
	
	if (graph[i][j] == 1 && !visited[i][j]) {
		graph[i][j] = dan;
		visited[i][j] = true;
		house_cnt++;

		DFS(i + 1, j);
		DFS(i - 1, j);
		DFS(i, j + 1);
		DFS(i, j - 1);
	}
}

int main(void) {

	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf_s("%1d", &graph[i][j]);
		}
	}

	vector<int> houses;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!visited[i][j] && graph[i][j]) {
				dan++;
				DFS(i, j);
				houses.push_back(house_cnt);
				house_cnt = 0;
			}
		}
	}

	cout << dan << '\n';

	sort(houses.begin(), houses.end());
	for (int i : houses)
		cout << i << '\n';

	return 0;
}