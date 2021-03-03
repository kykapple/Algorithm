#include <iostream>
#include <algorithm>
using namespace std;
#define MAX 1000001

int n, m, k;
long long tree[MAX];
long long segtree[MAX * 4];

long long make_segtree(int start, int end, int cur) {
    if (start == end) return segtree[cur] = tree[start];

    int mid = (start + end) / 2;
    return segtree[cur] = (make_segtree(start, mid, cur * 2) * make_segtree(mid + 1, end, cur * 2 + 1) % 1000000007);
}

long long change_segtree(int start, int end, int cur, int idx, long long value) {
    if (end < idx || idx < start) return segtree[cur];   // 해당 인덱스가 범위 내에 없을 경우

    if(start == end) return segtree[cur] = value;

    int mid = (start + end) / 2;
    return segtree[cur] = (change_segtree(start, mid, cur * 2, idx, value) * change_segtree(mid + 1, end, cur * 2 + 1, idx, value) % 1000000007);
}

long long get_mul(int start, int end, int cur, int left, long long right) {
    if (end < left || right < start) return 1;  // 범위 밖의 경우 기존 값을 반환

    if (left <= start && end <= right) return segtree[cur]; // 범위 내의 경우

    int mid = (start + end) / 2;
    return (get_mul(start, mid, cur * 2, left, right) * get_mul(mid + 1, end, cur * 2 + 1, left, right) % 1000000007);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    cin >> n >> m >> k;
    for (int i = 1; i <= n; i++)
        cin >> tree[i];

    make_segtree(1, n, 1);

    for (int i = 0; i < m + k; i++) {
        int a, b, c;
        cin >> a >> b >> c;

        switch (a) {
        case 1:
            change_segtree(1, n, 1, b, c);
            break;
        case 2:
            cout << get_mul(1, n, 1, b, c) << '\n';
            break;
        }
    }
    
    return 0;
}