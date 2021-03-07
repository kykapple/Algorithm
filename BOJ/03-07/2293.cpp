#include <iostream>
#include <algorithm>
using namespace std;

int coin[101];
int dp[10001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int n, k;
    cin >> n >> k;

    for (int i = 1; i <= n; i++)
        cin >> coin[i];

    dp[0] = 1;  // 만드려는 가치와 동전의 가치가 같을 경우를 위함
    for (int i = 1; i <= n; i++) {
        for (int j = coin[i]; j <= k; j++) {
            dp[j] += dp[j - coin[i]];
        }
    }

    cout << dp[k];

    return 0;
}