#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

vector<string> vec;
int alpha[26];

bool compare(int a, int b) {
    return a > b;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        string str;
        cin >> str;

        vec.push_back(str);
    }

    for (int i = 0; i < vec.size(); i++) {
        int p = 1;
        for (int j = vec[i].length()-1; j >= 0; j--) {
            char ch = vec[i][j];
            alpha[ch - 'A'] += p;
            p *= 10;
        }
    }

    sort(alpha, alpha + 26, compare);

    int num = 9, ans = 0;
    for (int i = 0; i <= 26; i++) {
        if (!alpha[i]) break;
        ans += (alpha[i] * num);
        num--;
    }

    cout << ans;

    return 0;
}