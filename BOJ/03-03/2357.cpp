#include <iostream>
#include <algorithm>
using namespace std;
#define MAX 100001

int n, m;
int tree[MAX];
pair<int, int> segtree[MAX * 4];

pair<int, int> make_segtree(int start, int end, int cur) {
    if (start == end) {
        segtree[cur].first = tree[start];
        segtree[cur].second = tree[start];

        return segtree[cur];
    }

    int mid = (start + end) / 2;

    pair<int, int> left = make_segtree(start, mid, cur * 2);
    pair<int, int> right = make_segtree(mid + 1, end, cur * 2 + 1);

    segtree[cur].first = min(left.first, right.first);
    segtree[cur].second = max(left.second, right.second);

    return segtree[cur];
}

pair<int,int> get_ans(int start, int end, int cur, int left, int right) {
    if (end < left || right < start) return make_pair(1e9 + 1, 0);

    if (left <= start && end <= right) {
        return make_pair(segtree[cur].first, segtree[cur].second);
    }

    int mid = (start + end) / 2;
    pair<int, int> left_p = get_ans(start, mid, cur * 2, left, right);
    pair<int, int> right_p = get_ans(mid + 1, end, cur * 2 + 1, left, right);

    int min_val = min(left_p.first, right_p.first);
    int max_val = max(left_p.second, right_p.second);

    return make_pair(min_val, max_val);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    cin >> n >> m;
    for (int i = 1; i <= n; i++)
        cin >> tree[i];

    make_segtree(1, n, 1);

    for (int i = 0; i < m; i++) {
        int left, right;
        cin >> left >> right;

        pair<int, int> ans = get_ans(1, n, 1, left, right);
        cout << ans.first << ' ' << ans.second << '\n';
    }

    return 0;
}