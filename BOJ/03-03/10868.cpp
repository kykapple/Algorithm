#include <iostream>
#include <algorithm>
using namespace std;
#define MAX 100001

int n, m;
int tree[MAX];
int segtree[MAX * 4];

int make_segtree(int start, int end, int cur) {
    if (start == end) return segtree[cur] = tree[start];

    int mid = (start + end) / 2;
    return segtree[cur] = min(make_segtree(start, mid, cur * 2), make_segtree(mid + 1, end, cur * 2 + 1));
}

int get_min(int start, int end, int cur, int left, int right) {
    if (end < left || right < start) return 1e9 + 1;    // 범위 밖의 경우 무한대 반환
    
    if (left <= start && end <= right) return segtree[cur]; // 범위 안의 경우 make_segtree()를 통해 구해놓은 최소값 반환

    int mid = (start + end) / 2;
    return min(get_min(start, mid, cur * 2, left, right), get_min(mid + 1, end, cur * 2 + 1, left, right));
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

        cout << get_min(1, n, 1, left, right) << '\n';
    }

    return 0;
}