#include <bits/stdc++.h>
using namespace std;

const int MAX = 100001;
const int INF = 1e9;
int graph[MAX];
bool visited[MAX];
int n, k;

void Dijkstra(int start) {
	visited[start] = true;
	graph[start] = 0;

	priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
	pq.push({ 0, start });

	while (!pq.empty()) {
		int dist = pq.top().first;
		int point = pq.top().second;
		pq.pop();

		if (dist > graph[point]) continue;

		int dx[] = { -1,1,point };
		for (int i = 0; i < 3; i++) {
			int nx = point + dx[i];

			if (nx < 0 || nx >= MAX) continue;
			if (!visited[nx]) {
				if (i == 2) {
					if (graph[nx] > graph[point]) {
						graph[nx] = graph[point];
						pq.push({ graph[nx], nx });
					}
				}
				else {
					if (graph[nx] > graph[point] + 1) {
						graph[nx] = graph[point] + 1;
						pq.push({ graph[nx], nx });
					}
				}
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> k;
	fill(graph, graph + MAX, INF);

	Dijkstra(n);

	cout << graph[k];

	return 0;
}