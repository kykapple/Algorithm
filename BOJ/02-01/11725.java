#include <bits/stdc++.h>
using namespace std;

int n;
vector<int> tree[100001];
int visited[100001];
int root[100001];

void BFS() {

	queue<int> q;
	q.push(1);

	while (!q.empty()) {
		int x = q.front();
		q.pop();

		for (int i = 0; i < tree[x].size(); i++) {
			if (!visited[tree[x][i]]) {
				root[tree[x][i]] = x;
				q.push(tree[x][i]);
			}
		}
		visited[x] = true;
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n;

	for (int i = 0; i < n - 1; i++) {
		int u, v;
		cin >> u >> v;

		tree[u].push_back(v);
		tree[v].push_back(u);
	}

	BFS();

	for (int i = 2; i <= n; i++)
		cout << root[i] << '\n';
		
	return 0;
}