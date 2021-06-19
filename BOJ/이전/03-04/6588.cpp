#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
#define MAX 1000000

bool val[1000001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    for (int i = 2; i <= MAX; i++) {
        if (val[i]) continue;
        for (int j = i + i; j <= MAX; j += i)
            val[j] = true;
    }

    while (true) {
        int n;
        cin >> n;

        if (n == 0) break;

        vector<int> vec;
        bool flag = false;
        for (int i = 2; i <= MAX; i++) {
            if (!val[i]) {
                if (n - i > 2 && !val[n - i]) {
                    vec.push_back(i);
                    vec.push_back(n - i);
                    flag = true;
                    break;
                }
            }
        }

        if (flag)
            cout << n << " = " << vec[0] << " + " << vec[1] << '\n';
        else
            cout << "Goldbach's conjecture is wrong." << '\n';
    }

    return 0;
}