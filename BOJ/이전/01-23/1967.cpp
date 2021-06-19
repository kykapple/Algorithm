#include <bits/stdc++.h>
using namespace std;

const int MAX = 10001;
vector<pair<int, int>> tree[MAX];
bool visited[MAX];
int result_idx, result_len;

void DFS(int u, int len) {
	if (visited[u]) return;

	visited[u] = true;

	if (result_len < len) {
		result_len = len;
		result_idx = u;
	}

	for (int i = 0; i < tree[u].size(); i++) {
		DFS(tree[u][i].first, len + tree[u][i].second);
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	for (int i = 0; i < n - 1; i++) {
		int u, v, w;
		cin >> u >> v >> w;

		tree[u].push_back({ v,w });
		tree[v].push_back({ u,w });
	}

	DFS(1, 0);
	for (int i = 0; i <= n; i++) visited[i] = false;
	DFS(result_idx, 0);

	cout << result_len;
	
	return 0;
}