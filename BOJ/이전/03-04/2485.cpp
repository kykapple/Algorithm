#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;
vector<int> vec;

int euclidean(int max, int min) {
    while (min) {
        int temp = max % min;
        max = min;
        min = temp;
    }
    
    return max;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    for (int i = 0; i < n; i++) {
        int v;
        cin >> v;

        vec.push_back(v);
    }

    sort(vec.begin(), vec.end());

    int gap = vec[n - 1] - vec[n - 2];
    for (int i = n-2; i > 0; i--) {
        gap = euclidean(gap, vec[i] - vec[i - 1]);
    }

    int ans = 0;
    for (int i = n - 1; i > 0; i--) {
        int temp = vec[i] - vec[i - 1];
        ans += (temp - gap) / gap;
    }

    cout << ans;

    return 0;
}