#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, neg;
bool flag;
vector<int> vec;

int solve() {
    int i, ans = 0;
    for (i = 0; i < neg; i += 2) {
        if (i == neg - 1)
            break;
        else {
            int a = vec[i] * vec[i + 1];
            int b = vec[i] + vec[i + 1];

            if (a > b)
                ans += a;
            else
                ans += b;
        }
    }

    for (int j = n-1; j >= i; j -= 2) {
        if (j == i)
            ans += vec[i];
        else {
            int a = vec[j] * vec[j - 1];
            int b = vec[j] + vec[j - 1];

            if (a > b)
                ans += a;
            else
                ans += b;
        }
    }

    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    cin >> n;

    int max_abs = 0;
    for (int i = 0; i < n; i++) {
        int v;
        cin >> v;

        vec.push_back(v);
        if (v < 0)
            neg++;
    }

    sort(vec.begin(), vec.end());

    cout << solve();

    return 0;
}
