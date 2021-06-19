#include <iostream>
#include <algorithm>
using namespace std;

int com[31][31];

int combination(int n, int r) {
    if (n == r || r == 0) return 1;
    if (com[n][r]) return com[n][r];

    return com[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int t;
    cin >> t;

    for (int i = 0; i < t; i++) {
        int a, b;
        cin >> a >> b;

        cout << combination(b, a) << '\n';
    }
    
    return 0;
}