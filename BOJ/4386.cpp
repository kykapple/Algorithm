#include <bits/stdc++.h>
using namespace std;

int n;
vector<pair<double,double>> vec;
vector<pair<double, pair<int, int>>> lens;
int parent[100];

int find(int a) {
    if (parent[a] == a) return parent[a];
    return parent[a] = find(parent[a]);
}

void union_point(int a, int b) {
    a = find(a);
    b = find(b);
    if (a < b) parent[b] = a;
    else parent[a] = b;
}

int main(void) {
    cin >> n;
    for (int i = 0; i < n; i++) {
        double x, y;
        cin >> x >> y;

        vec.push_back({ x,y });
    }

    for (int i = 0; i < n; i++) {
        double ax = vec[i].first;
        double ay = vec[i].second;

        for (int j = i + 1; j < n; j++) {
            double bx = vec[j].first;
            double by = vec[j].second;

            double dist = sqrt((ax - bx) * (ax - bx) + (ay - by) * (ay - by));

            lens.push_back({ dist,{i,j} });
        }
    }

    sort(lens.begin(), lens.end());

    for (int i = 0; i < n; i++)
        parent[i] = i;

    double ans = 0;
    int cnt = 0;
    for (int i = 0; i < lens.size(); i++) {
        double cost = lens[i].first;
        int start = lens[i].second.first;
        int end = lens[i].second.second;

        if (find(start) == find(end)) continue;

        union_point(start, end);
        ans += cost;
        cnt++;

        if (cnt == n - 1) break;
    }

    printf("%.2lf", ans);

    return 0;
}