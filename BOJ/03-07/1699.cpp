#include <iostream>
#include <algorithm>
using namespace std;

bool val[100001];
int dp[100001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int n;
    cin >> n;

    for (int i = 1; i * i <= n; i++) {
        val[i * i] = true;
    }

    fill(dp, dp + n + 1, 100001);

    for (int i = 1; i <= n; i++) {
        if (val[i])
            dp[i] = 1;
        else {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = min(dp[i], dp[j * j] + dp[i - (j * j)]);
            }
        }
    }

    cout << dp[n];

    return 0;
}