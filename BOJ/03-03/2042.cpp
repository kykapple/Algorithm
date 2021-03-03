#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int n, m, k;
long long tree[1000001];
long long segtree[1048576 * 2];

long long make_segtree(int start, int end, int cur) {
    if (start == end) return segtree[cur] = tree[start];     // 단말 노드일 경우

    int mid = (start + end) / 2;
    return segtree[cur] = make_segtree(start, mid, cur * 2) + make_segtree(mid + 1, end, cur * 2 + 1);
}

long long change_segtree(int start, int end, int cur, int idx, long long value) {   // 리프 노드부터 갱신
    if (end < idx || idx < start) return segtree[cur];
    
    if (start == end) return segtree[cur] = value;

    int mid = (start + end) / 2;
    return segtree[cur] = change_segtree(start, mid, cur * 2, idx, value) + change_segtree(mid + 1, end, cur * 2 + 1, idx, value);
}

long long get_sum(int start, int end, int cur, int left, long long right) {
    if (end < left || right < start) return 0;

    if (left <= start && end <= right) return segtree[cur];

    int mid = (start + end) / 2;
    return get_sum(start, mid, cur * 2, left, right) + get_sum(mid + 1, end, cur * 2 + 1, left, right);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m >> k;

    for (int i = 1; i <= n; i++) {
        int v;
        cin >> v;

        tree[i] = v;
    }

    make_segtree(1, n, 1);

    for (int i = 0; i < m + k; i++) {
        int a, b;
        long long c;
        cin >> a >> b >> c;

        switch (a) {
        case 1:
            change_segtree(1, n, 1, b, c);
            break;
        case 2:
            cout << get_sum(1, n, 1, b, c) << '\n';
            break;
        }
    }

    return 0;
}
