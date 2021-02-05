#include <bits/stdc++.h>
using namespace std;

vector<int> vec[1001];
int times[1001];
int entry[1001];
int dp[1001];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int t;
	cin >> t;

	for (int i = 0; i < t; i++) {

		int n, k;
		cin >> n >> k;

		for (int j = 1; j <= n; j++)
			cin >> times[j];

		for(int j=0; j<k; j++) {
			int u, v;
			cin >> u >> v;

			vec[u].push_back(v);
			entry[v]++;
		}

		int point;
		cin >> point;

		queue<int> q;
		for (int j = 1; j <= n; j++) {
			if (entry[j] == 0) {
				q.push(j);
				dp[j] = times[j];
			}
		}

		while(!q.empty()) {
			int x = q.front();
			q.pop();

			if (x == point) break;

			for (int k = 0; k < vec[x].size(); k++) {
				int y = vec[x][k];
				dp[y] = max(dp[y], times[y] + dp[x]);
				if(--entry[y] == 0)
					q.push(y);
			}
		}

		while (!q.empty()) q.pop();

		cout << dp[point] << '\n';

		for (int j = 1; j <= n; j++) {
			vec[j].clear();
			times[j] = 0;
			entry[j] = 0;
			dp[j] = 0;
		}
	}

	return 0;
}