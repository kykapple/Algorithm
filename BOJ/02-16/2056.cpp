#include <bits/stdc++.h>
using namespace std;

int n;
int times[10001];
int entry[10001];
int dp[10001];
vector<int> vec[10001];

int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    for (int i = 1; i <= n; i++) {
        cin >> times[i];
        
        int cnt;
        cin >> cnt;

        for (int j = 0; j < cnt; j++) {
            int work;
            cin >> work;

            vec[work].push_back(i);
            entry[i]++;
        }
    }

    queue<int> q;
    for (int i = 1; i <= n; i++) {
        if (entry[i] == 0) {
            q.push(i);
            dp[i] = times[i];
        }
    }

    while (!q.empty()) {
        int x = q.front();
        q.pop();

        for (int i = 0; i < vec[x].size(); i++) {
            int y = vec[x][i];
            dp[y] = max(dp[y], dp[x] + times[y]);
            if (--entry[y] == 0)
                q.push(y);
        }
    }

    int ans = 0;
    for (int i = 1; i <= n; i++)
        ans = max(ans, dp[i]);

    cout << ans;

    return 0;
}
