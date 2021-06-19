#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

int n;
priority_queue<int, vector<int>, greater<int>> pq;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    cin >> n;
    for (int i = 0; i < n; i++) {
        int v;
        cin >> v;

        pq.push(v);
    }

    int ans = 0;
    while (!pq.empty()) {
        int a = pq.top();
        pq.pop();

        if (!pq.empty()) {
            int b = pq.top();
            pq.pop();

            ans += (a + b);
            pq.push(a + b);
        }
    }

    cout << ans;

    return 0;
}