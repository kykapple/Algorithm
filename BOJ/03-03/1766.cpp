#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int n, m;
int entry[32001];
vector<int> vec[32001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    cin >> n >> m;

    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;

        vec[a].push_back(b);
        entry[b]++;
    }

    priority_queue<int, vector<int>, greater<int>> pq;
    for (int i = 1; i <= n; i++) {
        if (!entry[i])
            pq.push(i);
    }

    queue<int> ans;
    while(!pq.empty()) {
        int x = pq.top();
        pq.pop();

        ans.push(x);

        for (int j = 0; j < vec[x].size(); j++) {
            int y = vec[x][j];
            if (--entry[y] == 0)
                pq.push(y);
        }
    }

    while (!ans.empty()) {
        cout << ans.front() << " ";
        ans.pop();
    }

    return 0;
}
