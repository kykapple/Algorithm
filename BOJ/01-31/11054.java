#include <bits/stdc++.h>
using namespace std;

int values[1000];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	vector<int> upper(n);
	vector<int> lower(n);
	for (int i = 0; i < n; i++)
		cin >> values[i];

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			if (values[j] < values[i])
				upper[i] = max(upper[i], upper[j]);
		}
		upper[i]++;
	}

	for (int i = n - 1; i >= 0; i--) {
		for (int j = n - 1; j > i; j--) {
			if (values[j] < values[i])
				lower[i] = max(lower[i], lower[j]);
		}
		lower[i]++;
	}

	int ans = 0;
	for (int i = 0; i < n; i++)
		ans = max(ans, upper[i] + lower[i]);

	cout << ans - 1;

	return 0;
}