#include <bits/stdc++.h>
using namespace std;

int dp[500][500];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j <= i; j++) {
			cin >> dp[i][j];
			
			if (i > 1) {
				if (j == 0)
					dp[i][j] += dp[i - 1][j];
				else if (j == i)
					dp[i][j] += dp[i - 1][j - 1];
				else {
					int val = max(dp[i - 1][j], dp[i - 1][j - 1]);
					dp[i][j] += val;
				}
			}
			else if (i == 1) {
				dp[i][j] += dp[i - 1][0];
			}
			int val = dp[i][j];
		}
	}
	
	int max_val = 0;
	for (int i = 0; i < n; i++) {
		max_val = max(max_val, dp[n-1][i]);
	}

	cout << max_val;

	return 0;
}
