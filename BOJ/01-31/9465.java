#include <bits/stdc++.h>
using namespace std;

int dp[2][100000];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int t;
	cin >> t;

	for (int i = 0; i < t; i++) {
		int n;
		cin >> n;

		vector<int> sticker[2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < n; j++) {
				int score;
				cin >> score;

				sticker[i].push_back(score);
			}
		}

		dp[0][0] = sticker[0][0];
		dp[1][0] = sticker[1][0];
		
		int result = 0;
		if (n == 1)
			result = max(dp[0][0], dp[1][0]);
		else {
			dp[0][1] = dp[1][0] + sticker[0][1];
			dp[1][1] = dp[0][0] + sticker[1][1];
			result = max(dp[0][1], dp[1][1]);

			for (int j = 2; j < n; j++) {
				for (int i = 0; i < 2; i++) {
					dp[i][j] = max(dp[0][j - 2] + sticker[i][j], dp[1][j - 2] + sticker[i][j]);
					if (i == 0)
						dp[i][j] = max(dp[i][j], dp[1][j - 1] + sticker[i][j]);
					else
						dp[i][j] = max(dp[i][j], dp[0][j - 1] + sticker[i][j]);
					result = max(result, dp[i][j]);
				}
			}
		}

		cout << result << '\n';
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = 0;
			}
		}
	}

	return 0;
}