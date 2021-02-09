#include <bits/stdc++.h>
using namespace std;

int n;
int times[501];
int entry[501];
int dp[501];
vector<int> vec[501];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n;

	for (int i = 1; i <= n; i++) {
		int time;
		cin >> time;

		times[i] = time;

		while (true) {
			int before;
			cin >> before;

			if (before == -1) break;
			entry[i]++;
			vec[before].push_back(i);
		}
	}

	queue<int> q;
	for (int i = 1; i <= n; i++) {
		if (entry[i] == 0) {
			q.push(i);
			dp[i] = times[i];
		}
	}

	while (!q.empty()) {
		int x = q.front();
		q.pop();

		for (int i = 0; i < vec[x].size(); i++) {
			int y = vec[x][i];
			dp[y] = max(dp[y], dp[x] + times[y]);
			if (--entry[y] == 0)
				q.push(y);
		}
	}

	for (int i = 1; i <= n; i++)
		cout << dp[i] << '\n';

	return 0;
}
