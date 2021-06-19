#include <bits/stdc++.h>
using namespace std;

vector<pair<int, int>> vec;
bool visited[102];
int n;

void BFS(int x) {

	queue<int> q;
	q.push(x);

	while (!q.empty()) {
		int dx = q.front();
		q.pop();

		visited[dx] = true;
		for (int i = 0; i < n + 2; i++) {
			if (abs(vec[dx].first - vec[i].first) + abs(vec[dx].second - vec[i].second) <= 1000) {
				if (!visited[i])
					q.push(i);
			}
		}
	}
}

void DFS(int x) {
	visited[x] = true;

	for (int i = 0; i < n + 2; i++) {
		if (abs(vec[x].first - vec[i].first) + abs(vec[x].second - vec[i].second) <= 1000) {
			if (!visited[i])
				DFS(i);
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	
	int t;
	cin >> t;

	for (int i = 0; i < t; i++) {
		cin >> n;

		int x, y;
		for (int j = 0; j < n + 2; j++) {
			cin >> x >> y;

			vec.push_back({ x,y });
		}

		DFS(0);
		
		if (visited[n + 1])
			cout << "happy" << '\n';
		else
			cout << "sad" << '\n';

		for (int j = 0; j < n + 2; j++) {
			visited[j] = false;
		}
		vec.clear();
	}

	return 0;
}