#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	vector<int> dp(12, 0);

	dp[1] = 1;
	dp[2] = 2;
	dp[3] = 4;

	for (int i = 4; i <= 11; i++) {
		for (int j = i - 1; j > 0; j--) {
			if (i - j <= 3)
				dp[i] += dp[j];
		}
	}

	vector<int> result;
	for (int i = 0; i < n; i++) {
		int val;
		cin >> val;

		result.push_back(dp[val]);
	}

	for (int i : result)
		cout << i << "\n";

	return 0;
}