#include <bits/stdc++.h>
using namespace std;

int n;
int A[4000];
int B[4000];
int C[4000];
int D[4000];
vector<int> AB;
vector<int> CD;

int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> A[i] >> B[i] >> C[i] >> D[i];
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            AB.push_back(A[i] + B[j]);
            CD.push_back(C[i] + D[j]);
        }
    }

    sort(AB.begin(), AB.end());

    long long cnt = 0;
    for (int i = 0; i < CD.size(); i++) {
        auto lower = lower_bound(AB.begin(), AB.end(), -CD[i]);
        auto upper = upper_bound(AB.begin(), AB.end(), -CD[i]);

        cnt += (upper - lower);
    }

    cout << cnt;

    return 0;
}