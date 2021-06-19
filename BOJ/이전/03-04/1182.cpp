#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, s, ans;
vector<int> vec;
vector<int> temp;

void tracking(int start, int cnt, int limit) {
    if (cnt == limit) {
        int sum = 0;
        for (int i = 0; i < cnt; i++)
            sum += temp[i];

        if (sum == s) ans++;
        return;
    }

    for (int i = start; i < n; i++) {
        temp.push_back(vec[i]);
        tracking(i + 1, cnt + 1, limit);
        temp.pop_back();
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> s;
    for (int i = 0; i < n; i++) {
        int v;
        cin >> v;

        vec.push_back(v);
    }

    for (int j = 1; j <= n; j++) {
        tracking(0, 0, j);
    }

    cout << ans;

    return 0;
}