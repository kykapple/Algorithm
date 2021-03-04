#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;
vector<int> vec;
vector<int> lotto;

void tracking(int start, int cnt) {
    if (cnt == 6) {
        for (int i = 0; i < cnt; i++)
            cout << lotto[i] << ' ';
        cout << '\n';

        return;
    }

    for (int i = start; i < n; i++) {
        lotto.push_back(vec[i]);
        tracking(i + 1, cnt + 1);
        lotto.pop_back();
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    while (true) {
        cin >> n;

        if (n == 0) break;

        for (int i = 0; i < n; i++) {
            int v;
            cin >> v;

            vec.push_back(v);
        }

        tracking(0, 0);

        cout << '\n';
        vec.clear();
    }

    return 0;
}
