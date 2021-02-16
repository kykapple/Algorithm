#include <bits/stdc++.h>
using namespace std;

vector<int> vec[32001];
int entry[32001];

int main(void) {
    
    int n, m;
    cin >> n >> m;

    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;

        vec[a].push_back(b);
        entry[b]++;
    }

    queue<int> q;
    for (int i = 1; i <= n; i++) {
        if (entry[i] == 0)
            q.push(i);
    }

    while (!q.empty()) {
        int x = q.front();
        q.pop();
        cout << x << ' ';

        for (int i = 0; i < vec[x].size(); i++) {
            int y = vec[x][i];
            if (--entry[y] == 0)
                q.push(y);
        }
    }

    return 0;
}