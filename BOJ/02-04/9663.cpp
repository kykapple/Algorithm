#include <bits/stdc++.h>
using namespace std;

int n;
int chess[15][15];
int ans;
vector<pair<int, int>> vec;

void bt(int start, int cnt) {
	if (start >= 2) {
		int x = vec.back().first;
		int y = vec.back().second;
		for (int i = 0; i < vec.size() - 1; i++) {
			if ((abs(x - vec[i].first) == abs(y - vec[i].second)) || x == vec[i].first || y == vec[i].second) return;
		}
	}
	
	if (cnt == n) {
		ans++;
		return;
	}

	for (int i = 0; i < n; i++) {
		chess[i][start] = 1;
		vec.push_back({ i,start });
		bt(start + 1, cnt + 1);
		vec.pop_back();
		chess[i][start] = 0;
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n;

	bt(0, 0);

	cout << ans;

	return 0;
}