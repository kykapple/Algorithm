#include <iostream>
#include <algorithm>
using namespace std;

int n;
int card_pack[1001];
int dp[1001][1001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    for (int i = 1; i <= n; i++)
        cin >> card_pack[i];

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);

            if (i <= j)
                dp[i][j] = max(dp[i][j], dp[i][i] + dp[i - 1][j - i]);
            
            if (j % i == 0) 
                dp[i][j] = max(dp[i][j], card_pack[i] * (j / i));
           
        }
    }

    cout << dp[n][n];

    return 0;
}