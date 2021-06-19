#include <iostream>
#include <algorithm>
using namespace std;

long long dp[1001][1001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int n;
    cin >> n;

    for (int i = 0; i <= 9; i++) {
        dp[1][i] = 1;
    }

    for (int i = 2; i <= n; i++) {
        for (int j = 0; j <= 9; j++) {
            for (int k = j; k <= 9; k++) {
                dp[i][j] += dp[i - 1][k];
            }
            dp[i][j] %= 10007;
        }
    }

    int sum = 0;
    for (int i = 0; i <= 9; i++)
        sum += dp[n][i];

    cout << sum % 10007;

    return 0;
}