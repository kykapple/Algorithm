#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, m;
vector<int> vec;
vector<int> cost;
pair<int,int> app[101];
int dp[101][10001];

bool compare(pair<int, int> p1, pair<int, int> p2) {
    if (p1.second < p2.second)
        return true;
    else if (p1.second == p2.second)
        return p1.first < p2.first;
    else
        return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        int m;
        cin >> m;

        vec.push_back(m);
    }

    for (int i = 0; i < n; i++) {
        int c;
        cin >> c;

        cost.push_back(c);
    }

    for (int i = 1; i <= n; i++) {
        app[i] = { vec[i - 1], cost[i - 1] };
    }

    sort(app, app + n + 1, compare);

    int ans = 1e9;
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= 10000; j++) {
            if (app[i].second <= j)
                dp[i][j] = app[i].first + dp[i - 1][j - app[i].second];
            dp[i][j] = max(dp[i][j], dp[i - 1][j]);

            if (dp[i][j] >= m) {
                ans = min(ans, j);
                break;
            }
        }
    }

    cout << ans;

    return 0;
}
