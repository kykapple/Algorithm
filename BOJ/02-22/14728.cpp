#include <bits/stdc++.h>
using namespace std;

int times[101];
int score[101];
int dp[101][10001];

int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int n, t;  
    cin >> n >> t;

    for (int i = 1; i <= n; i++) {
        cin >> times[i] >> score[i];
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= t; j++) {
            if (j >= times[i])
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - times[i]] + score[i]);
            else
                dp[i][j] = dp[i - 1][j];
        }
    }

    cout << dp[n][t];

    return 0;
}