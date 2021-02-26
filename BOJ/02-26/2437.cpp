#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;
vector<int> vec;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    cin >> n;

    for (int i = 0; i < n; i++) {
        int v;
        cin >> v;

        vec.push_back(v);
    }

    sort(vec.begin(), vec.end());

    if (vec[0] != 1)
        cout << 1;
    else {
        int sum = vec[0];
        for (int i = 1; i < n; i++) {
            if (sum + 1 < vec[i]) break;
            sum += vec[i];
        }
        cout << sum + 1;
    }

    return 0;
}