#include <bits/stdc++.h>
using namespace std;

int dp[101][100001]; // 가치합을 저장할 배열
int weight[101];	// 무게
int value[101];	// 가치

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n, k;
	cin >> n >> k;

	for (int i = 1; i <= n; i++) {
		int w, v;
		cin >> w >> v;

		weight[i] = w;
		value[i] = v;
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= k; j++) {
			if (weight[i] <= j)
				dp[i][j] = value[i] + dp[i - 1][j - weight[i]];
			dp[i][j] = max(dp[i][j], dp[i - 1][j]);
		}
	}

	cout << dp[n][k];

	return 0;
}