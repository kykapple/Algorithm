#include <iostream>
#include <algorithm>
using namespace std;
#define MAX 100001

int n, q;
long long arr[MAX];
long long segtree[MAX * 4];

long long make_segtree(int start, int end, int cur) {
    if (start == end) return segtree[cur] = arr[start]; // 리프 노드일 경우

    int mid = (start + end) / 2;
    return segtree[cur] = make_segtree(start, mid, cur * 2) + make_segtree(mid + 1, end, cur * 2 + 1);
}

long long change_segtree(int start, int end, int cur, int idx, long long value) {
    if (end < idx || idx < start) return segtree[cur];

    if (start == end) return segtree[cur] = value;

    int mid = (start + end) / 2;
    return segtree[cur] = change_segtree(start, mid, cur * 2, idx, value) + change_segtree(mid + 1, end, cur * 2 + 1, idx, value);
}

long long get_sum(int start, int end, int cur, int left, int right) {
    if (end < left || right < start) return 0;

    if (left <= start && end <= right) return segtree[cur];

    int mid = (start + end) / 2;
    return get_sum(start, mid, cur * 2, left, right) + get_sum(mid + 1, end, cur * 2 + 1, left, right);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> q;
    for (int i = 1; i <= n; i++)
        cin >> arr[i];

    make_segtree(1, n, 1);

    for (int i = 0; i < q; i++) {
        int x, y, a, b;
        cin >> x >> y >> a >> b;

        int left = 0, right = 0;
        if (x < y) {
            left = x;
            right = y;
        }
        else {
            left = y;
            right = x;
        }
        cout << get_sum(1, n, 1, left, right) << '\n';
        change_segtree(1, n, 1, a, b);
    }

    return 0;
}