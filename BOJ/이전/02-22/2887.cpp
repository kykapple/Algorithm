#include <bits/stdc++.h>
using namespace std;

struct planet {
    int x;
    int y;
    int z;
    int num;
};

int n;
vector<planet> vec;
vector <pair<int, pair<int, int>>> lens;
int parent[100000];

bool compX(planet p1, planet p2) {
    return p1.x < p2.x;
}

bool compY(planet p1, planet p2) {
    return p1.y < p2.y;
}

bool compZ(planet p1, planet p2) {
    return p1.z < p2.z;
}

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
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    for (int i = 0; i < n; i++) {
        int x, y, z;
        cin >> x >> y >> z;

        vec.push_back({ x,y,z,i });
    }

    sort(vec.begin(), vec.end(), compX);
    for (int i = 0; i < vec.size() - 1; i++) {
        int ax = vec[i].x;
        int bx = vec[i + 1].x;

        int dist = abs(ax - bx);
        lens.push_back({ dist,{vec[i].num, vec[i + 1].num} });
    }

    sort(vec.begin(), vec.end(), compY);
    for (int i = 0; i < vec.size() - 1; i++) {
        int ay = vec[i].y;
        int by = vec[i + 1].y;

        int dist = abs(ay - by);
        lens.push_back({ dist,{vec[i].num, vec[i + 1].num} });
    }

    sort(vec.begin(), vec.end(), compZ);
    for (int i = 0; i < vec.size() - 1; i++) {
        int az = vec[i].z;
        int bz = vec[i + 1].z;

        int dist = abs(az - bz);
        lens.push_back({ dist,{vec[i].num, vec[i + 1].num} });
    }

    sort(lens.begin(), lens.end());

    for (int i = 0; i < n; i++)
        parent[i] = i;

    int ans = 0, cnt = 0;
    for (int i = 0; i < lens.size(); i++) {
        int cost = lens[i].first;
        int start = lens[i].second.first;
        int end = lens[i].second.second;

        if (find(start) == find(end)) continue;

        union_point(start, end);
        ans += cost;
        cnt++;

        if (cnt == n - 1) break;
    }

    cout << ans;

    return 0;
}
