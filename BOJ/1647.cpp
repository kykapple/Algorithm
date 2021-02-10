#include <bits/stdc++.h>
using namespace std;

int parent[100001];
vector < pair<int, pair<int, int>>> vec;

int find(int n) {
	return parent[n] == n ? n : find(parent[n]);
}

void union_point(int a, int b) {
	a = find(a);
	b = find(b);
	if (a < b) parent[b] = a;
	else parent[a] = b;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n, m;
	cin >> n >> m;

	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;

		vec.push_back({ c,{a,b} });
	}

	sort(vec.begin(), vec.end());

	for (int i = 1; i <= n; i++)
		parent[i] = i;

	int ans = 0, sep = 0;
	for (int i = 0; i < vec.size(); i++) {
		int cost = vec[i].first;
		int start = vec[i].second.first;
		int end = vec[i].second.second;

		if(find(start) == find(end)) continue;

		union_point(start, end);
		ans += cost;
		sep = max(sep, cost);
	}

	cout << ans - sep;

	return 0;
}