#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int n, k;
int tab[100];
vector<int> values;
queue<int> idx[101];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    cin >> n >> k;
    for (int i = 0; i < k; i++) {
        int v;
        cin >> v;

        values.push_back(v);
        idx[v].push(i);
    }

    int ans = 0;
    for (int i = 0; i < k; i++) {
        bool is_empty = false, already = false;;
        int empty_idx = 0;
        for (int j = 0; j < n; j++) {
            if (tab[j] == values[i]) {
                already = true;
                break;
            }
            else if (!tab[j]) {
                is_empty = true;
                empty_idx = j;
                break;
            }
        }

        if (already) {
            idx[values[i]].pop();
            continue;
        }

        if (is_empty) { // 빈 구멍이 남아있으면
            tab[empty_idx] = values[i];
            idx[values[i]].pop();
        }
        else {  // 멀티탭이 꽉 찬 경우
            // 꽂혀있는 용품 중 교체할 용품 선택
            int change_idx = 0, temp = 0;
            for (int j = 0; j < n; j++) {
                if (idx[tab[j]].empty()) {  // 나중에 다시 사용되지 않는 용품일 경우
                    change_idx = j;
                    break;
                }
                else if (temp < idx[tab[j]].front()) {
                    temp = idx[tab[j]].front();   // 가장 나중에 사용되는 용품 선택
                    change_idx = j;
                }
            }

            bool flag = false;
            for (int j = 0; j < n; j++) {
                if (tab[j] == values[i]) {  // 이미 멀티탭에 해당 용품이 꽂혀있을 경우
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                tab[change_idx] = values[i];
                ans++;
            }

            idx[values[i]].pop();
        }
    }

    cout << ans;

    return 0;
}