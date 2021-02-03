#include <bits/stdc++.h>
using namespace std;

char str1[1000];
char str2[1000];
int dp[1001][1001];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> str1;
	cin >> str2;

	for (int i = 1; i <= strlen(str1); i++) {
		for (int j = 1; j <= strlen(str2); j++) {
			if (str1[i - 1] == str2[j - 1]) {
				dp[i][j] = dp[i - 1][j - 1] + 1;
			}
			else {
				dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
	}

	cout << dp[strlen(str1)][strlen(str2)];

	return 0;
}