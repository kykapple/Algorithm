#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

vector<pair<int, int>> graph[10001];
int arr[10001];

void Dijkstra(int start) {

	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	pq.push({ 0,start });
	arr[start] = 0;

	while (!pq.empty()) {
		int dist = pq.top().first;
		int point = pq.top().second;
		pq.pop();

		if (dist > arr[point]) continue;

		for (int i = 0; i < graph[point].size(); i++) {
			if (arr[graph[point][i].first] > dist + graph[point][i].second) {
				arr[graph[point][i].first] = dist + graph[point][i].second;
				pq.push({ arr[graph[point][i].first], graph[point][i].first });
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int t;
	cin >> t;

	for (int i = 0; i < t; i++) {
		int n, d, c;
		cin >> n >> d >> c;	// 시작점 : c, 정점 개수 : n

		for (int j = 0; j < d; j++) {
			int v, u, w;
			cin >> v >> u >> w;

			graph[u].push_back({ v,w });
		}

		fill(arr, arr + 10001, INF);
		Dijkstra(c);

		int cnt = 0, time = 0;
		for (int j = 1; j <= n; j++) {
			if (arr[j] != INF) {
				cnt++;
				time = max(time, arr[j]);
			}
		}

		cout << cnt << " " << time << '\n';
		
		for (int j = 1; j <= n; j++)
			graph[j].clear();
	}

	return 0;
}