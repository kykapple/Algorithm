#include <bits/stdc++.h>
using namespace std;

int coins[10];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n, k;
	cin >> n >> k;

	for (int i = 0; i < n; i++)
		cin >> coins[i];

	int idx = 0;
	for (int i = 0; i < n; i++) {
		if (coins[i] > k) {
			idx = i - 1;
			break;
		}
		idx = i;
	}

	int cnt = 0;
	for (int i = idx; i >= 0; i--) {
		cnt += k / coins[i];
		k %= coins[i];
	}

	cout << cnt;

	return 0;
}