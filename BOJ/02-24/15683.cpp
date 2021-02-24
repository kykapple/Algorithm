#include <bits/stdc++.h>
using namespace std;

int n, m, amount, result = 1e9;
vector<vector<int>> graph(8, vector<int>(8, 0));
int kind[6];
vector<pair<int, pair<int, int>>> cctv;

void left(int x, int y) {
    for (int j = y + 1; j < m; j++) {
        if (graph[x][j] == 6) break;
        if (graph[x][j] != 9) graph[x][j] = 9;
    }
}

void right(int x, int y) {
    for (int j = y - 1; j >= 0; j--) {
        if (graph[x][j] == 6) break;
        if (graph[x][j] != 9) graph[x][j] = 9;
    }
}

void up(int x, int y) {
    for (int i = x - 1; i >= 0; i--) {
        if (graph[i][y] == 6) break;
        if (graph[i][y] != 9) graph[i][y] = 9;
    }
}

void down(int x, int y) {
    for (int i = x + 1; i < n; i++) {
        if (graph[i][y] == 6) break;
        if (graph[i][y] != 9) graph[i][y] = 9;
    }
}

void first(int x, int y, int flag) {
    switch (flag) {
    case 0:
        return up(x, y);
    case 1:
        return left(x, y);
    case 2:
        return down(x, y);
    case 3:
        return right(x, y);
    }
}

void second(int x, int y, int flag) {
    switch (flag) {
    case 0:
        left(x, y);
        right(x, y);
        break;
    case 1:
        up(x, y);
        down(x, y);
        break;
    }
}

void third(int x, int y, int flag) {
    switch (flag) {
    case 0:
        up(x, y);
        right(x, y);
        break;
    case 1:
        up(x, y);
        left(x, y);
        break;
    case 2:
        left(x, y);
        down(x, y);
        break;
    case 3:
        right(x, y); 
        down(x, y);
        break;
    }
}

void fourth(int x, int y, int flag) {
    switch (flag) {
    case 0:
        up(x, y);
        right(x, y);
        left(x, y);
        break;
    case 1:
        up(x, y);
        left(x, y);
        down(x, y);
        break;
    case 2:
        left(x, y);
        down(x, y);
        right(x, y);
        break;
    case 3:
        right(x, y);
        down(x, y);
        up(x, y);
        break;
    }
}

void fifth(int x, int y, int flag) {
    up(x, y); 
    right(x, y);
    left(x, y);
    down(x, y);
}

void caller(int k, int x, int y, int flag) {
    switch (k) {
    case 1:
        return first(x, y, flag);
    case 2:
        return second(x, y, flag);
    case 3:
        return third(x, y, flag);
    case 4:
        return fourth(x, y, flag);
    case 5:
        return fifth(x, y, flag);
    }
}

void solve(int start, int cnt) {
    if (cnt == amount) {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!graph[i][j])
                    sum++;
            }
        }

        result = min(result, sum);

        return;
    }

    vector<vector<int>> mapp = graph;

    int k = cctv[start].first;
    int x = cctv[start].second.first;
    int y = cctv[start].second.second;

    for (int i = 0; i < kind[k]; i++) {
        caller(k, x, y, i);
        solve(start + 1, cnt + 1);
        graph = mapp;
    }
}

int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> graph[i][j];

            if (graph[i][j] && graph[i][j] != 6) {
                cctv.push_back({ graph[i][j],{i,j} });
                amount++;
            }
        }
    }

    kind[1] = 4;
    kind[2] = 2;
    kind[3] = 4;
    kind[4] = 4;
    kind[5] = 1;

    solve(0, 0);

    cout << result;

    return 0;
}
